/*LOGIN SUBSYSTEM*/

package sample.Api;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class EmailApi {

    private Map<String, Object> settings;
    private String emailVar;

    public EmailApi(String emailVar) throws IOException, ParseException {

        this.emailVar = emailVar;

        settings = new LinkedHashMap<>();
        settings.put("to", emailVar);
        settings.put("from", "Zorg-For-You");
        settings.put("subject", "Wachtwoord vergeten!");
        settings.put("message", "<head>\n" +
                "    <style>\n" +
                "        body{\n" +
                "            line-height: 0.5;\n" +
                "            text-align: center;\n" +
                "            font-family: 'Courier New', Courier, monospace;\n" +
                "        }\n" +
                "        h1{\n" +
                "            margin-top: 10px;\n" +
                "            margin-bottom: 2em;\n" +
                "        }\n" +
                "        p{\n" +
                "            line-height: 1em;\n" +
                "        }\n" +
                "\n" +
                "        .styling{\n" +
                "            line-height: 1.5em;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Wachtwoord vergeten!</h1>\n" +
                "\n" +
                "    <p>Neem contact op met ons via \"zorg-for-you@hotmail.com\". </p>\n" +
                "        \n" +
                "    <p>Wij zullen contact met u opnemen om uw wachtwoord te veranderen!</p>\n" +
                "\n" +
                "    \n" +
                "    <p>Veel werkplezier & hopelijk heeft u een fijne ervaring met onze app!</p>\n" +
                "\n" +
                "    <br>\n" +
                "    \n" +
                "    <p class=\"styling\">Met vriendelijke groeten</p>\n" +
                "    \n" +
                "    <p class=\"styling\">Team Zorg-For-You 02</p>\n" +
                "</body>\n" +
                "</html>");

    }

    public Map<String, Object> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, Object> settings) {
        this.settings = settings;
    }
}
