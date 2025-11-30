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
  // 常用时区列表
  private static final List<String> TIMEZONES = Arrays.asList(
          "America/New_York",
          "America/Los_Angeles",
          "America/Chicago",
          "Europe/London",
          "Europe/Paris",
          "Asia/Tokyo",
          "Asia/Shanghai",
          "Australia/Sydney"
  );
  // 语言区域设置
  private static final List<String> LOCALES = Arrays.asList(
          "en-US",
          "en-GB",
          "zh-CN",
          "ja-JP",
          "fr-FR",
          "de-DE",
          "es-ES"
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
            .setUserAgent(userAgent)
            .setLocale(LOCALES.get(random.nextInt(LOCALES.size())))
            .setTimezoneId(TIMEZONES.get(random.nextInt(TIMEZONES.size())))
    );

    Page page = context.newPage();
// 替换固定的viewport设置
    int width = 1280 + random.nextInt(640);  // 1280-1920之间
    int height = 800 + random.nextInt(400);  // 800-1200之间
    page.setViewportSize(width, height);

    // 关键：隐藏 webdriver 属性
    page.addInitScript("Object.defineProperty(navigator, 'webdriver', { get: () => undefined });");

    // 可选：屏蔽常见 bot 检测脚本（进阶）
    page.addInitScript("""
            window.chrome = { runtime: {} };
            Object.defineProperty(navigator, 'permissions', {
              get: () => ({ query: Promise.resolve.bind(Promise) })
            });
            """);
// 在createStealthPage方法中添加更多初始化脚本
    page.addInitScript("""
                // 伪装WebGL渲染器
                const getParameter = WebGLRenderingContext.prototype.getParameter;
                WebGLRenderingContext.prototype.getParameter = function(parameter) {
                    if (parameter === 37445) return 'Intel Inc.';
                    if (parameter === 37446) return 'Intel Iris OpenGL Engine';
                    return getParameter.apply(this, arguments);
                };
            
                // 伪装canvas
                HTMLCanvasElement.prototype.toDataURL = function() {
                    return '';
                };
            
                // 伪装plugins和mimeTypes
                navigator.__defineGetter__('plugins', function() {
                    return {
                        length: 0,
                        item: function() { return null; },
                        namedItem: function() { return null; }
                    };
                });
            
                navigator.__defineGetter__('mimeTypes', function() {
                    return {
                        length: 0,
                        item: function() { return null; },
                        namedItem: function() { return null; }
                    };
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