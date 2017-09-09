import java.io.*;
import java.util.*;

public class PaperMaker
{
  public static final String fileName = "MyPaper.txt";
  private static FileWriter f = null;
  private static BufferedWriter bw = null;
  private static Boolean shouldConclude = false;
  private static StringBuilder content = new StringBuilder();
  private static int bodyIndex = 0;
  private static Scanner in = null;
  private static String line = "";

  public static void main(String[] args)
  {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
    {
      System.out.println("Welcome to PaperMaker!");
      createIntro();
      createThesis();

      while (!shouldConclude)
      {
        createBody();
      }

      createConclusion();

      bw.write(content.toString());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      in.close();
    }
  }

  private static void createIntro()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("\t");
    sb.append(singlePrompt("Opening line: "));
    sb.append(repeatPrompt("Background Info: "));
    sb.append(repeatPrompt("So what? "));
    sb.append("\n");
    content.append(sb.toString());
  }

  private static void createThesis()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("\t");
    sb.append(singlePrompt("What is your topic or subject matter? "));
    sb.append(singlePrompt("What are you claiming? "));
    sb.append(repeatPrompt("What evidence do you have? "));
    sb.append(singlePrompt("What do others say? "));
    sb.append(repeatPrompt("What is your argument? "));
    sb.append(singlePrompt("How will you argue this? "));
    sb.append("\n");
    content.append(sb.toString());
  }

  private static void createBody()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("\t");

    if (bodyIndex == 0)
    {
      sb.append(singlePrompt("What counts as evidence? "));
      sb.append(singlePrompt("How is this evidence relevant to your claim? "));
      sb.append(repeatPrompt("By what standard will you analyze evidence? "));
      sb.append("\n");
      content.append(sb.toString());
      bodyIndex++;
      return;
    }

    String m = singlePrompt("What is the main idea of this paragraph? ");

    if (m.equals(""))
    {
      shouldConclude = true;
      return;
    }

    sb.append(m);

    String evidence = singlePrompt("Evidence: ");

    while (!evidence.equals(""))
    {
      sb.append(evidence);
      sb.append(singlePrompt("Analysis: "));
      evidence = singlePrompt("Evidence: ");
    }

    sb.append(singlePrompt("What do others say? "));
    sb.append(singlePrompt("What do you say? "));
    sb.append(singlePrompt("Link back to larger claim: "));
    sb.append(singlePrompt("Conclude: "));

    bodyIndex++;
    sb.append("\n");
    content.append(sb.toString());
  }

  private static void createConclusion()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("\t");
    sb.append(singlePrompt("Restate your claim: "));
    sb.append(repeatPrompt("Restate your argument: "));
    sb.append(repeatPrompt("So what? "));
    sb.append(singlePrompt("Closing line: "));
    sb.append("\n");
    content.append(sb.toString());
  }

  private static String singlePrompt(String prompt)
  {
    StringBuilder sb = new StringBuilder();
    System.out.println(prompt);
    in = new Scanner(System.in);
    line = in.nextLine();
    if (line.equals("")) return "";
    sb.append(line);
    sb.append(" ");
    return sb.toString();
  }

  private static String repeatPrompt(String prompt)
  {
    StringBuilder sb = new StringBuilder();
    System.out.println(prompt);
    in = new Scanner(System.in);
    line = in.nextLine();
    if (line.equals("")) return "";
    while (!line.equals(""))
    {
      sb.append(line);
      sb.append(" ");
      System.out.println(prompt);
      in = new Scanner(System.in);
      if (in.hasNextLine()) line = in.nextLine();
      else line = "";
    }

    return sb.toString();
  }
}
