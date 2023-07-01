package dmf_demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class InfoServlet extends HttpServlet {

    @Override
    /**
     * Return JSON information of Seq<Seq<String>> object; uses servlet import.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestUrl = request.getRequestURI();
            String ssnNum = requestUrl.substring("/ssn/".length());

            Seq<String> info = ReturnField.ssnInfo("input-tests/dmf/dmf1.csv", ssnNum);
            String json = ReturnField.toJson(info);

            if (json != null) {

                response.getOutputStream().println(json);
            } else {
                // Else if person was not found after searching using SSN
                response.getOutputStream().println("{}");
            }


        } catch (IOException e) {
            System.err.println("java.io.FileNotFoundException: missing.csv "
                    + "(No such file or directory)");
        } //catch (ArrayIndexOutOfBoundsException e) {
//            System.err.println("Error: person could not be found (from ssn)");
//        }
    }


}
