public class Aufgabe6 {

    final static int MAX_WORD_LENGTH = 20;

    public static void main(String[] args) throws Exception {
        int switchVar = args.length == 0 ? 3 : Integer.parseInt(args[0]);
        System.out.printf("Bitte ein Beispielwort eingeben (max %d Zeichen): ", MAX_WORD_LENGTH);
        char[] textArray = new char[MAX_WORD_LENGTH];
        for (int i = 0; i < textArray.length; i++) {
            char inputChar = (char)System.in.read();
            if (inputChar == '\r' || inputChar == '\n') {
                if (i == 0) {
                    System.out.println("Ungültige Eingabe!");
                    return;
                }
                break;
            }
            textArray[i] = inputChar;
        }

        switch (switchVar) {
        case 1: // Ersetzen
            System.out.print("[1] Zu ersetzenden Buchstaben eingeben, dann den Ersatz: ");
            char charToReplace = (char) System.in.read();
            // Setze auf -1 um auf Nicht-Vorhandensein prüfen zu können
            int indexToReplace = -1;
            for (int i = 0; i < textArray.length; i++) {
                if (textArray[i] != charToReplace)
                    continue;
                indexToReplace = i;
                break;
            }
            if (indexToReplace == -1) { // Wenn nicht vorhanden
                System.out.println("Ungültige Eingabe!");
                return;
            }
            char replacingChar = (char) System.in.read();
            textArray[indexToReplace] = replacingChar;
            System.out.print("Verändertes Array: ");
            System.out.println(textArray);
            break;
        case 2: // Löschen per index
            System.out.print("[2] Zu löschenden Index eingeben (0 basiert): ");
            char inputOne = (char) System.in.read();
            // Prüfe ob die Eingabe eine Zahl ist
            if (inputOne < '0' || inputOne > '9') {
                System.out.println("Ungültige Eingabe!");
                return;
            }
            int indexToDelete = Character.getNumericValue(inputOne);
            // Prüfen ob Eingabe im Array liegt
            if (indexToDelete < 0 || indexToDelete > textArray.length) {
                System.out.println("Ungültige Eingabe!");
                return;
            }
            char[] changedArray = new char[textArray.length - 1];
            int counter = 0;
            while (counter < textArray.length) {
                if (counter == indexToDelete) {
                    counter++;
                    continue;
                }
                if (counter > indexToDelete)
                    changedArray[counter - 1] = textArray[counter];
                else
                    changedArray[counter] = textArray[counter];
                counter++;
            }
            textArray = changedArray;
            System.out.print("Verändertes Array: ");
            System.out.println(textArray);
            break;
        case 3: // Löschen aller Vorkommen
            System.out.print("[3] Zu löschenden Buchstaben eingeben: ");
            char charToDelete = (char) System.in.read();
            counter = 0; // Anzahl der Vorkommen von charToDelete
            // Finde alle Vorkommen von charToDelete und speichere die Indezes
            int[] indexesToDelete = new int[textArray.length];
            for (int i = 0; i < textArray.length; i++) {
                if (textArray[i] == charToDelete) {
                    indexesToDelete[counter] = i;
                    counter++;
                }
            }
            if (counter == 0) { // Wenn kein Vorkommen vorhanden ist
                System.out.println("Ungültige Eingabe!");
                return;
            }
            changedArray = new char[textArray.length - counter];
            // wird wiederverwendet
            counter = 0;
            int lowerBoundary = 0;
            int upperBoundary = indexesToDelete[0];
            for (int i = 0; i < textArray.length; i++) {
                if (indexesToDelete[counter] == i) {
                    counter++;
                    continue;
                }
                changedArray[i - counter] = textArray[i];
            }
            textArray = changedArray;
            System.out.print("Verändertes Array: ");
            System.out.println(textArray);
            break;
        case 4: // Einfügen
            System.out.print("[4] Zu verwendenden Index angeben (0 basiert): ");
            char inputChar = (char) System.in.read();
            if (inputChar < '0' && inputChar > '9') {
                System.out.println("Ungültige Eingabe!");
                return;
            }
            int indexToInsert = Character.getNumericValue(inputChar);
            if (indexToInsert > textArray.length) {
                System.out.println("Ungültige Eingabe!");
                return;
            }
            changedArray = new char[textArray.length + 1];
            for (int i = 0; i < changedArray.length; i++) {
                if (i == indexToInsert) {
                    changedArray[i] = '-';
                    continue;
                } else if (i > indexToInsert)
                    changedArray[i] = textArray[i - 1];
                else if (i < indexToInsert)
                    changedArray[i] = textArray[i];
            }
            textArray = changedArray;
            System.out.print("Verändertes Array: ");
            System.out.println(textArray);
            break;
        case 5: // Vergleichen
            System.out.printf("Zu vergleichenden String angeben (max %d Zeichen): ", MAX_WORD_LENGTH);
            char[] substring = new char[MAX_WORD_LENGTH];
            int substringLength = 3;
            for (substringLength = 0; substringLength < MAX_WORD_LENGTH; substringLength++) {
                inputChar = (char) System.in.read();
                if (inputChar == '\r' || inputChar == '\n') {
                    if (substringLength == 0) {
                        System.out.println("Ungültige Eingabe!");
                        return;
                    }
                    break;
                }
                substring[substringLength] = inputChar;
            }

            boolean isSubstring = false;
            int index;
            for (int i = 0; i < textArray.length; i++) {
                // Finde Startsequenz
                if (textArray[i] == substring[0]) {
                    index = i;
                    for (int j = 1; j < substringLength; j++) {
                        int nextIndex = i + j;
                        if (nextIndex >= textArray.length)
                            continue;
                        if (textArray[nextIndex] != substring[j]) {
                            isSubstring = false;
                            break;
                        }
                        else
                            isSubstring = true;
                    }
                    if (isSubstring) {
                        System.out.print(substring);
                        System.out.print(" kommt in ");
                        System.out.print(textArray);
                        System.out.printf(" an Stelle %d vor (1 basiert)%n", index+1);
                        return;
                    }
                }
            }
            System.out.print(substring);
            System.out.print(" kommt in ");
            System.out.print(textArray);
            System.out.printf(" gar nicht vor%n");
        }
    }
}