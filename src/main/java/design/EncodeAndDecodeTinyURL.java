package design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 11/04/2017. TinyURL is a URL shortening service where you enter
 * a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as
 * http://tinyurl.com/4e9iAk.
 *
 * <p>Design the encode and decode methods for the TinyURL service. There is no restriction on how
 * your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a
 * tiny URL and the tiny URL can be decoded to the original URL.
 */
public class EncodeAndDecodeTinyURL {
  private List<String> list = new ArrayList<>();
  private static final String URL = "http://tinyurl.com/";

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    EncodeAndDecodeTinyURL encoder = new EncodeAndDecodeTinyURL();
    String shorterUrl = encoder.encode("https://leetcode.com/problems/design-tinyurl");
    System.out.println(encoder.decode(shorterUrl));
  }

  // Encodes a URL to a shortened URL.
  public String encode(String longUrl) {
    list.add(longUrl);
    return URL.concat(String.valueOf(list.size()));
  }

  // Decodes a shortened URL to its original URL.
  public String decode(String shortUrl) {
    String[] parts = shortUrl.split("http://tinyurl.com/");
    String code = parts[1];
    return list.get(Integer.parseInt(code) - 1);
  }
}
