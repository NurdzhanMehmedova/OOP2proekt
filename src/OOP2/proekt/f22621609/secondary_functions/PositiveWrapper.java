package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositiveWrapper implements FileHandler {
    private FileOpener fileOpener;

    public PositiveWrapper(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }

    @Override
    public void processing() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the automaton to create a positive wrapper of: ");
        String automatonId = scanner.nextLine().trim();

        StringBuilder fileContent = fileOpener.getFileContent();
        if (fileContent != null) {
            if (isValidAutomatonId(fileContent.toString(), automatonId)) {
                String positiveWrapper = findPositiveWrapper(fileContent.toString(), automatonId);
                if (positiveWrapper != null) {
                    String fileName = "positive" + automatonId + ".txt";
                    try (FileWriter writer = new FileWriter(fileName)) {
                        writer.write(positiveWrapper);
                        System.out.println("Positive Wrapper for Automaton '" + automatonId + "' has been created in file: " + fileName);
                    } catch (IOException e) {
                        System.out.println("Failed to write positive wrapper to file.");
                    }
                } else {
                    System.out.println("Failed to create positive wrapper for Automaton '" + automatonId + "'.");
                }
            } else {
                System.out.println("Invalid automaton ID: '" + automatonId + "'.");
            }
        } else {
            System.out.println("No file content found.");
        }
    }

    private boolean isValidAutomatonId(String fileContent, String automatonId) {
        String automatonPattern = "<automaton\\s+id=\"" + automatonId + "\"[^>]*>";
        return fileContent.matches("(?s).*" + automatonPattern + ".*");
    }

    private String findPositiveWrapper(String fileContent, String automatonId) {
        String automatonStartPattern = "<automaton\\s+id=\"" + automatonId + "\"[^>]*>";
        String transitionsStartPattern = "<transitions>";
        String automatonEndPattern = "</automaton>";

        Pattern automatonStartRegex = Pattern.compile(automatonStartPattern);
        Matcher automatonStartMatcher = automatonStartRegex.matcher(fileContent);
        if (!automatonStartMatcher.find()) {
            return null;
        }
        int automatonStartIndex = automatonStartMatcher.start();
        int automatonEndIndex = fileContent.indexOf(automatonEndPattern, automatonStartIndex);

        String automatonXml = fileContent.substring(automatonStartIndex, automatonEndIndex + automatonEndPattern.length());

        int transitionsStartIndex = automatonXml.indexOf(transitionsStartPattern);
        if (transitionsStartIndex == -1) {
            return null;
        }

        StringBuilder positiveWrapperXml = new StringBuilder(automatonXml);
        String positiveWrapperTransitions = createPositiveWrapperTransitions(automatonXml.substring(transitionsStartIndex));
        if (positiveWrapperTransitions == null) {
            return null;
        }

        positiveWrapperXml.insert(
                transitionsStartIndex + transitionsStartPattern.length(),
                "<transition>\n"
                        + "    <fromState>qε</fromState>\n"
                        + "    <toState>" + extractInitialStates(automatonXml) + "</toState>\n"
                        + "    <inputSymbol>ε</inputSymbol>\n"
                        + "</transition>"
        );
        positiveWrapperXml.insert(
                transitionsStartIndex + transitionsStartPattern.length(),
                "\n" + positiveWrapperTransitions + "\n"
        );
        return positiveWrapperXml.toString();
    }

    private String extractInitialStates(String automatonXml) {
        Pattern pattern = Pattern.compile("<initialState>(.*?)</initialState>");
        Matcher matcher = pattern.matcher(automatonXml);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private String createPositiveWrapperTransitions(String transitionsXml) {
        String transitionPattern = "<transition>.*?</transition>";

        Pattern transitionRegex = Pattern.compile(transitionPattern, Pattern.DOTALL);
        Matcher transitionMatcher = transitionRegex.matcher(transitionsXml);

        StringBuilder positiveWrapperTransitions = new StringBuilder();

        while (transitionMatcher.find()) {
            String transitionXml = transitionMatcher.group();

            String fromState = extractTagContent(transitionXml, "fromState");
            String toState = extractTagContent(transitionXml, "toState");
            String inputSymbol = extractTagContent(transitionXml, "inputSymbol");

            positiveWrapperTransitions.append("<transition>\n");
            positiveWrapperTransitions.append("    <fromState>").append(toState).append("</fromState>\n");
            positiveWrapperTransitions.append("    <toState>").append(fromState).append("</toState>\n");
            positiveWrapperTransitions.append("    <inputSymbol>").append(inputSymbol).append("</inputSymbol>\n");
            positiveWrapperTransitions.append("</transition>\n");
        }

        return positiveWrapperTransitions.toString();
    }


    private String extractTagContent(String input, String tagName) {
        Pattern pattern = Pattern.compile("<" + tagName + ">(.*?)</" + tagName + ">");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
