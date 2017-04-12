import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by gouthamvidyapradhan on 11/04/2017.
 * Accepted
 */
public class EncodeAndDecodeTinyURL
{
    private List<String> list = new ArrayList<>();
    private static final String URL = "http://tinyurl.com/";
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        EncodeAndDecodeTinyURL encoder = new EncodeAndDecodeTinyURL();
        String shorterUrl = encoder.encode("https://leetcode.com/problems/design-tinyurl");
        System.out.println(encoder.decode(shorterUrl));
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl)
    {
        list.add(longUrl);
        return URL.concat(String.valueOf(list.size()));
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl)
    {
        String[] parts = shortUrl.split("http://tinyurl.com/");
        String code = parts[1];
        return list.get(Integer.parseInt(code) - 1);
    }

}
