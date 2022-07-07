import color.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Домашнее задание 4
 * Задание 2
 * Напишите шуточную программу «Дешифратор», которая бы в текстовом файле могла бы заменить все предлоги на слово «Java».
 */

public class Decoder {
    public static void main(String[] args) {
        List<String> stringArrayListFile = new ArrayList<>();
        String path = "info.txt";
        File file = new File(path);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                stringArrayListFile.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не был найден (при чтении), рекомендуем проверить: \n1. наличее файла, 2. места его расположения, 3. правильности пути к нему");
        }
        try (PrintWriter pwNewFile = new PrintWriter(file)) {
            System.out.println(Color.GREEN + "Дешифрований текст:" + Color.DEFAULT);
            for (String s : stringArrayListFile) {
                Pattern pattern = Pattern.compile("\\s(до)\\s|\\s(на)\\s|\\s(под)\\s|\\s(за)\\s|\\s(к)\\s|\\s(из)\\s|\\s(по)\\s|\\s(об)\\s|\\s(от)\\s|\\s(в)\\s|\\s(у)\\s|\\s(с)\\s|\\s(о)\\s|\\s(над)\\s|\\s(около)\\s|\\s(со)\\s|\\s(про)\\s|\\s(для)\\s|\\s(при)\\s|\\s(без)\\s"); //  поможет составить регулярное выражение https://regexr.com/
                Matcher matcher = pattern.matcher(s);
                // matcher.replaceAll("Java");
                System.out.println(matcher.replaceAll(" Java "));
                pwNewFile.println(matcher.replaceAll(" Java "));
            }
            System.out.println(Color.GREEN + "Текстовый файл : info.txt (находится в корне проекта) был удачно «Дешифрован» - все предлоги заменены на слово «Java»!" + Color.DEFAULT);
            System.out.println(Color.GREEN + "Для собственного теста: Вы можете заменить текст в info.txt или указать путь (path) к своему файлу в переменной типа - String path = \"..... *.txt\"" + Color.DEFAULT);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не был найден (при записи), рекомендуем проверить: \n1. наличее файла, 2. места его расположения, 3. правильности пути к нему");
        }

    }
}
