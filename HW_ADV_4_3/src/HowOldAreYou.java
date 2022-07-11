import color.Color;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Домашнее задание 4
 * Задание 3
 * Спроектируйте и разработайте метод, определяющий, сколько времени прошло с заданной даты.
 * С помощью этого методы выведите в консоль, сколько времени прошло с вашего дня рождения в удобном для восприятия виде,
 * например: «Вам исполнилось 20 лет, 3 месяца, 18 дней, 4 часа, 5 минут и 10 секунд».
 */

public class HowOldAreYou {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String strUserDate = "";
        boolean isDate = false;
        boolean isTime = false;
        LocalTime time = LocalTime.of(0, 0, 0);

        while (!isDate) {
            System.out.println(Color.GREEN + "Введите дату своего рождения в формате: \"dd.mm.yyyy\" например: 15.07.2007 :" + Color.DEFAULT);
            strUserDate = scanner.next();
            isDate = isValidDateBirthday(strUserDate);
            System.out.println(isDate ? Color.GREEN + "Данные успешно приняты!" + Color.DEFAULT :
                    Color.RED + "Данные не приняты! не пройдена проверка на формат: \"dd.mm.yyyy\" или логику" + Color.DEFAULT);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate myDate = LocalDate.parse(strUserDate, formatter);

        long timeStart1 = System.currentTimeMillis();
        System.out.println(Color.BLUE + "Вам исполнилось (вариант с LocalDate) 1-й метод : " + Color.YELLOW + getLiteDifferenceFromToday(myDate)[0] + " лет, " + getLiteDifferenceFromToday(myDate)[1] + " месяца, " + getLiteDifferenceFromToday(myDate)[2] + " дней" + Color.DEFAULT);
        System.out.println("Длительность расчетов 1-й метод : использовался класс Period и метод between = " + (System.currentTimeMillis() - timeStart1) + " мил.сек.");

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d - MMMM - yyyy");
        System.out.println("Твой день рождения : " + Color.GREEN + formatter2.format(myDate) + Color.DEFAULT);
        System.out.println("Дата сегодня       : " + Color.GREEN + formatter2.format(LocalDate.now()) + Color.DEFAULT);

        LocalDateTime myDateTime = myDate.atTime(time);
        while (!isTime) {
            System.out.println(Color.GREEN + "Введите время (час) своего рождения, если Вам известно в формате: \"HH:MM:SS\" например: 17:30:46 \\ или нажмите 0 + Enter для формата: 00:00:00" + Color.DEFAULT);
            strUserDate = scanner.next();
            if (strUserDate.equals("0")) {
                myDateTime = myDate.atTime(time);
                System.out.println(Color.GREEN + "Данные успешно приняты 00:00:00" + Color.DEFAULT);
                break;
            }
            isTime = isValidTimeBirthday(strUserDate);
            System.out.println(isTime ? Color.GREEN + "Данные успешно приняты!" + Color.DEFAULT :
                    Color.RED + "Данные не приняты! не пройдена проверка на формат: \"HH:MM:SS\" или логику" + Color.DEFAULT);
            if (isTime) {
                String[] masTime = strUserDate.split(":");
                time = LocalTime.of(Integer.parseInt(masTime[0]), Integer.parseInt(masTime[1]), Integer.parseInt(masTime[2]));
                myDateTime = myDate.atTime(time);
                if (!myDateTime.isBefore(LocalDateTime.now())) {
                    isTime = false;
                }
            }
        }

        long timeStart4 = System.currentTimeMillis();
        System.out.println(Color.BLUE + "Вам исполнилось (вариант с LocalDateTime) 2-й метод : " + Color.YELLOW + getHardDifferenceFromToday(myDateTime)[0] + " лет, " + getHardDifferenceFromToday(myDateTime)[1] + " месяца, " + getHardDifferenceFromToday(myDateTime)[2] + " дней, " + getHardDifferenceFromToday(myDateTime)[3] + " часа, " + getHardDifferenceFromToday(myDateTime)[4] + " минут и " + getHardDifferenceFromToday(myDateTime)[5] + " секунд" + Color.DEFAULT);
        System.out.println("Длительность расчетов 2-й метод : использовался полностью свой метод = " + (System.currentTimeMillis() - timeStart4) + " мил.сек.");

        long timeStart5 = System.currentTimeMillis();
        System.out.println(Color.BLUE + "Вам исполнилось (вариант с LocalDateTime) 3-й метод : " + Color.YELLOW + getBetweenTwoDates(myDateTime, LocalDateTime.now())[0] + " лет, " + getBetweenTwoDates(myDateTime, LocalDateTime.now())[1] + " месяца, " + getBetweenTwoDates(myDateTime, LocalDateTime.now())[2] + " дней, " + getBetweenTwoDates(myDateTime, LocalDateTime.now())[3] + " часа, " + getBetweenTwoDates(myDateTime, LocalDateTime.now())[4] + " минут и " + getBetweenTwoDates(myDateTime, LocalDateTime.now())[5] + " секунд" + Color.DEFAULT);
        System.out.println("Длительность расчетов 3-й метод : использовался класс ChronoUnit и метод between = " + (System.currentTimeMillis() - timeStart5) + " мил.сек.");

        DateTimeFormatter formatMyTime = DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm:ss");
        System.out.println("Твой день рождения : " + Color.GREEN + formatMyTime.format(myDateTime) + Color.DEFAULT);
        System.out.println("Дата сегодня       : " + Color.GREEN + formatMyTime.format(LocalDateTime.now()) + Color.DEFAULT);

        scanner.close();
    }

    /**
     * метод isValidDateBirthday определяет, является ли строка strUserDate типа String
     * датой в формате "dd.MM.yyyy" типа String - эти данные метод
     * возвращает в переменной isDate типа boolean
     */
    private static boolean isValidDateBirthday(String strUserDate) {
        Pattern pattern = Pattern.compile("((0[1-9])|([12][0-9])|(3[01]))\\.((0[1-9])|(1[0-2]))\\.((19\\d\\d)|(20\\d\\d))");
        Matcher matcher = pattern.matcher(strUserDate);
        boolean isDate = matcher.matches();
        if (isDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDateBirthday = LocalDate.parse(strUserDate, formatter);
            if (!localDateBirthday.isBefore(LocalDate.now())) {
                isDate = false;
            }
        }
        return isDate;
    }

    /**
     * метод isValidDateTimeBirthday определяет, является ли строка strUserDate типа String
     * временем в формате "HH.mm.ss" типа String - эти данные метод
     * возвращает в переменной типа boolean
     */
    private static boolean isValidTimeBirthday(String strUserDate) {
        Pattern pattern = Pattern.compile("((([01]\\d)|(2[0-3])):(([0-5]\\d)|(2[0-3])):(([0-5]\\d)|(2[0-3])))");
        Matcher matcher = pattern.matcher(strUserDate);
        return matcher.matches();
    }


    /**
     * метод getBetweenTwoDates определяет, сколько между двумя датами (withDate и byDate) типа LocalDateTime
     * прошло времени в формате: лет:месяцев:дней:часов:минут:секунд - эти данные метод возвращает в int[6]
     * в котором: int[0] разница лет, int[1] разница месяцев, int[2] разница дней, int[3] разница часов,
     * int[4] разница минут, int[6] разница секунд
     */
    private static long[] getBetweenTwoDates(LocalDateTime withDate, LocalDateTime byDate) {
        long[] between = new long[6];
        between[0] = ChronoUnit.YEARS.between(withDate, byDate);
        long tempBetweenFullMonths = ChronoUnit.MONTHS.between(withDate, byDate);
        between[1] = tempBetweenFullMonths - 12 * between[0];
        long tempDayOfFullMonth = 0;
        LocalDate tempWithDate = LocalDate.from(withDate);
        for (int i = 0; i < tempBetweenFullMonths; i++) {
            tempDayOfFullMonth += tempWithDate.plusMonths(i).lengthOfMonth();
        }
        between[2] = ChronoUnit.DAYS.between(withDate, byDate) - tempDayOfFullMonth;
        between[3] = ChronoUnit.HOURS.between(withDate, byDate) - (ChronoUnit.DAYS.between(withDate, byDate) * 24);
        between[4] = ChronoUnit.MINUTES.between(withDate, byDate) - (ChronoUnit.HOURS.between(withDate, byDate) * 60);
        between[5] = ChronoUnit.SECONDS.between(withDate, byDate) - (ChronoUnit.MINUTES.between(withDate, byDate) * 60);
        return between;
    }

    /**
     * метод getLiteDifferenceFromToday : определяет в облегченной форме, сколько времени прошло
     * от полученой даты (переменная fromDate - типа LocalDate) по дату сегодня т.е. LocalDate.now().
     * Метод возвращает int[3] в котором int[0] разница лет, int[1] разница месяцев, int[2] разница дней
     */
    private static int[] getLiteDifferenceFromToday(LocalDate fromDate) {
        int[] between = new int[3];
        LocalDate today = LocalDate.now();
        Period period = Period.between(fromDate, today);
        between[0] = period.getYears();
        between[1] = period.getMonths();
        between[2] = period.getDays();
        return between;
    }

    /**
     * метод getDifferenceFromToday перегружен переменной типа LocalDateTime: определяет, сколько времени прошло
     * с полученной даты в метод и по дату сегодня т.е. LocalDateTime.now(). Метод принимает переменную
     * типа LocalDateTime и возвращает int[6] в котором
     * int[0] разница лет, int[1] разница месяцев, int[2] разница дней, int[3] разница часов, int[4] разница минут,
     * int[6] разница секунд
     */
    private static int[] getHardDifferenceFromToday(LocalDateTime fromDate) {
        int[] between = new int[6];
        LocalDateTime today = LocalDateTime.now();
        int deltaTime;
        if (fromDate.getSecond() > today.getSecond()) {
            between[5] = 60 - fromDate.getSecond() + today.getSecond();
            deltaTime = 1;
        } else {
            between[5] = today.getSecond() - fromDate.getSecond();
            deltaTime = 0;
        }
        if (fromDate.getMinute() == today.getMinute() && deltaTime == 1) {
            between[4] = 59;
        } else {
            if (fromDate.getMinute() > today.getMinute()) {
                between[4] = 60 - fromDate.getMinute() + today.getMinute() - deltaTime;
                deltaTime = 1;
            } else {
                between[4] = today.getMinute() - fromDate.getMinute() - deltaTime;
                deltaTime = 0;
            }
        }
        if (fromDate.getHour() == today.getHour() && deltaTime == 1) {
            between[3] = 23;
        } else {
            if (fromDate.getHour() > today.getHour()) {
                between[3] = 24 - fromDate.getHour() + today.getHour() - deltaTime;
                deltaTime = 1;
            } else {
                between[3] = today.getHour() - fromDate.getHour() - deltaTime;
                deltaTime = 0;
            }
        }
        if (fromDate.getDayOfMonth() == today.getDayOfMonth() && deltaTime == 1) {
            between[2] = LocalDate.now().minusMonths(1).lengthOfMonth() - 1;
        } else {
            if (fromDate.getDayOfMonth() > today.getDayOfMonth()) {
                between[2] = LocalDate.now().minusMonths(1).lengthOfMonth() - fromDate.getDayOfMonth() + today.getDayOfMonth() - deltaTime;
                deltaTime = 1;
            } else {
                between[2] = today.getDayOfMonth() - fromDate.getDayOfMonth() - deltaTime;
                deltaTime = 0;
            }
        }
        if (fromDate.getMonth() == today.getMonth() && deltaTime == 1) {
            between[1] = 11;
        } else {
            if (fromDate.getMonthValue() > today.getMonthValue()) {
                between[1] = 12 - fromDate.getMonthValue() + today.getMonthValue() - deltaTime;
                deltaTime = 1;
            } else {
                between[1] = today.getMonthValue() - fromDate.getMonthValue() - deltaTime;
                deltaTime = 0;
            }
        }
        between[0] = today.getYear() - fromDate.getYear() - deltaTime;
        return between;
    }

}
