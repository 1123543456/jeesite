package test;

import com.thinkgem.jeesite.common.web.BaseController;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "${adminPath}/demo")
public class DemoController extends BaseController {

	@RequestMapping(value = "/demo")
	public String demo(String url, Model model)  {
		String html = pickData(url);
		Almanac almanac = analyzeHTMLByString(html);
		model.addAttribute("almanac", almanac);
		return "demo/demo";
	}

	/*
	 * 爬取网页信息
	 */
	private static String pickData(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				// 打印响应状态
				if (entity != null) {
					return EntityUtils.toString(entity);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * 使用jsoup解析网页信息
	 */
	private static Almanac analyzeHTMLByString(String html) {
		String reviewsText, priceText, starsText, store = " ";
		Document document = Jsoup.parse(html);
		Almanac almanac = new Almanac();
		// 评论数
		reviewsText = document.getElementById("acrCustomerReviewText").text().toString();
		System.out.println(reviewsText);
		almanac.setReviews(reviewsText);
		// 价格
		priceText = document.getElementById("priceblock_saleprice").text().toString();
		priceText += document.getElementById("saleprice_shippingmessage").text().toString();
		System.out.println(priceText);
		almanac.setPrice(priceText);
		// 星级
		starsText = document.getElementById("acrPopover").text().toString();
		System.out.println(starsText);
		almanac.setStars(starsText);
		return almanac;
	}
}