import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";
    ChangeMachine changeORama = new ChangeMachine();

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/change", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Float userAmount = Float.parseFloat(request.queryParams("amount"));
      System.out.println(userAmount);
      String result = changeORama.makeChange(userAmount);
      model.put("result", result);
      model.put("template", "templates/change.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
