package org.mikufans.service.playwright;


import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StealthyBrowser {

  private static final Logger log = LoggerFactory.getLogger(StealthyBrowser.class);

  // 模拟常见 User-Agent 列表（可扩展）
  private static final List<String> USER_AGENTS = Arrays.asList(
          "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.6613.138 Safari/537.36",
          "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.6613.138 Safari/537.36",
          "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.6613.138 Safari/537.36 Edg/128.0.2792.75",
          "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.5 Safari/605.1.15",
          "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:129.0) Gecko/20100101 Firefox/129.0",
          "Mozilla/5.0 (Linux; Android 14; Pixel 8) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.6613.138 Mobile Safari/537.36",
          "Mozilla/5.0 (iPhone; CPU iPhone OS 17_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.5 Mobile/15E148 Safari/604.1",
          "Mozilla/5.0 (iPad; CPU OS 17_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.5 Mobile/15E148 Safari/604.1",
          "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.6613.138 Safari/537.36",
          "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.6478.127 Safari/537.36"
  );

  private static final Random random = new Random();

  /**
   * 获取一个“伪装”过的 Page 实例
   */
  public static Page createStealthPage(Playwright playwright) {
    String userAgent = USER_AGENTS.get(random.nextInt(USER_AGENTS.size()));

    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(true)
            .setArgs(Arrays.asList(
                    "--disable-blink-features=AutomationControlled",
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--disable-gpu",
                    "--disable-extensions",
                    "--disable-plugins",
                    "--mute-audio"
            ))
    );

    BrowserContext context = browser.newContext(new Browser.NewContextOptions()
            .setViewportSize(1920, 1080)
            .setUserAgent(userAgent)
            .setLocale("en-US")
            .setTimezoneId("America/New_York") // 可根据目标站点调整
    );

    Page page = context.newPage();

    // 关键：隐藏 webdriver 属性
    page.addInitScript("Object.defineProperty(navigator, 'webdriver', { get: () => undefined });");

    // 可选：屏蔽常见 bot 检测脚本（进阶）
    page.addInitScript("""
            window.chrome = { runtime: {} };
            Object.defineProperty(navigator, 'permissions', {
              get: () => ({ query: Promise.resolve.bind(Promise) })
            });
            """);

    return page;
  }

  /**
   * 安全关闭浏览器资源
   */
  public static void closePage(Page page) {
    try {
      if (page != null && !page.isClosed()) {
        page.context().browser().close();
      }
    } catch (Exception e) {
      log.warn("Error closing browser", e);
    }
  }
}