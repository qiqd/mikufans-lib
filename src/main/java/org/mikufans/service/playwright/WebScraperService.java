package org.mikufans.service.playwright;


import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WebScraperService {

  private static final Logger log = LoggerFactory.getLogger(WebScraperService.class);

  /**
   * 获取指定 URL 的完整渲染 HTML
   */
  public String fetchRenderedHtml(String url) {
    if (!url.startsWith("http")) {
      throw new IllegalArgumentException("URL must start with http(s)://");
    }

    Playwright playwright = null;
    Page page = null;
    try {
      playwright = Playwright.create();
      page = StealthyBrowser.createStealthPage(playwright);

      log.info("Navigating to: {}", url);
      page.navigate(url, new Page.NavigateOptions().setTimeout(30000)); // 30秒超时

      // 可选：等待关键元素出现（防止过早返回）
      // page.waitForSelector("#main-content", new Page.WaitForSelectorOptions().setTimeout(10000));

      // 等待网络空闲（推荐）
//      page.waitForLoadState(Page.LoadState.NETWORKIDLE);
      page.waitForTimeout(1000);
      String html = page.content();
      log.info("Successfully fetched HTML (length: {})", html.length());
      return html;

    } catch (Exception e) {
      log.error("Failed to fetch HTML from: {}", url, e);
      throw new RuntimeException("Scraping failed: " + e.getMessage(), e);
    } finally {
      if (page != null) {
        StealthyBrowser.closePage(page);
      }
      if (playwright != null) {
        playwright.close();
      }
    }
  }
}