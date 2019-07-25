package ru.pft.rest.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

public class TestBase {

  public boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json?limit=500")).returnContent().asString();
    JsonElement parse = new JsonParser().parse(json);
    JsonElement issues = parse.getAsJsonObject().get("issues");
    JsonElement issue = issues.getAsJsonArray().get(0);
    String issue_state = issue.getAsJsonObject().get("state_name").toString();
    return issue_state.contains("Open");
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
