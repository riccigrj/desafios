package idwall.desafio.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author ricci
 */
public class Crawler {

    public WebDriver driver;

    @FindBys({
        @FindBy(how = How.XPATH, xpath = "//div[contains(@class,'thing')]")})
    public List<WebElement> threads;

    @FindBy(how = How.CLASS_NAME, className = "next-button")
    public WebElement next;

    public Crawler() {
        this.driver = new FirefoxDriver();
        PageFactory.initElements(driver, this);
    }

    private List<idwall.desafio.model.Thread> crawl(List<WebElement> webThreads) {
        List<idwall.desafio.model.Thread> threads = new ArrayList<>();
        for (WebElement webThread : webThreads) {
            Long upvotes = Long.valueOf(webThread.getAttribute("data-score"));

            if (upvotes >= 5000) {
                WebElement webTitle = webThread.findElement(By.xpath("./div[2]/div[1]/p[1]/a"));
                //Populando Objeto Thread
                idwall.desafio.model.Thread thread = new idwall.desafio.model.Thread();
                thread.setTitulo(webTitle.getText());
                thread.setUpvotes(upvotes);
                thread.setLink(webThread.getAttribute("data-url"));
                thread.setLinkComentarios(webThread.getAttribute("data-permalink"));
                thread.setAutor(webThread.getAttribute("data-author"));
                thread.setSubreddit(webThread.getAttribute("data-subreddit"));

                threads.add(thread);

                if (webThread.equals(webThreads.get(webThreads.size() - 1)) && (upvotes > 5000)) {
                    WebDriverWait wait = new WebDriverWait(driver, 10);
                    next.click();
                    wait.until(ExpectedConditions.visibilityOfAllElements(webThreads));
                    threads.addAll(crawl(webThreads));
                }
            }
        }
        return threads;
    }

    public Map<String, List<idwall.desafio.model.Thread>> getThreadsMaisPopulares(String subreddits) {
        String[] listSubreddits = subreddits.split(";");
        Map<String, List<idwall.desafio.model.Thread>> subThreads = new HashMap();
        List<idwall.desafio.model.Thread> threads = new ArrayList<>();

        for (String subreddit : listSubreddits) {
            driver.get("https://www.old.reddit.com/r/" + subreddit + "/top/?sort=top&t=all");
            threads.addAll(crawl(this.threads));
            subThreads.put(subreddit, threads);
        }
        driver.quit();
        return subThreads;
    }

}
