import color.Color;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;


/**
 * Домашнее задание 4
 * Задание 3
 * Спроектируйте и разработайте метод, определяющий, сколько времени прошло с заданной даты.
 * С помощью этого методы выведите в консоль, сколько времени прошло с вашего дня рождения в удобном для восприятия виде,
 * например: «Вам исполнилось 20 лет, 3 месяца, 18 дней, 4 часа, 5 минут и 10 секунд».
 */

public class HowOldAreYou {
    public static void main(String[] args) {
        int myYear = 2007;
        int myMonth = 7;
        int myDayOfMonth = 27;
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Введите дату своего рождения в формате: \"dd.mm.yyyy\" например: 15.07.2007 ");
//        String strDate = scanner.next();
//        String[] masDate = strDate.split("\\.");
//        int myYear = Integer.parseInt(masDate[2]);
//        int myMonth = Integer.parseInt(masDate[1]);
//        int myDayOfMonth = Integer.parseInt(masDate[0]);
        LocalDate myDate = LocalDate.of(myYear, myMonth, myDayOfMonth);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd - MMMM - yyyy");
        System.out.println("Твой день рождения: " + Color.GREEN + formatter.format(myDate) + Color.DEFAULT);
        System.out.println("Дата сегодня      : " + Color.GREEN + formatter.format(today) + Color.DEFAULT);
        String strD = formatter.format(today);

        long timeStart2 = System.currentTimeMillis();
        System.out.println(Color.BLUE + "Вам исполнилось (вариант с LocalDate) : " + Color.YELLOW + getDifferenceFromToday(myDate)[0] + " лет, " + getDifferenceFromToday(myDate)[1] + " месяца, " + getDifferenceFromToday(myDate)[2] + " дней" + Color.DEFAULT);
        System.out.println("Длительность расчетов (вариант с LocalDate) = " + (System.currentTimeMillis() - timeStart2) + " мил.сек.");

//        System.out.println("Введите дату своего рождения в формате: \"hh:mm:ss\" например: 17:30:40 ");
//        String strTime = scanner.next();
//        String[] masTime = strTime.split(":");
//        int myHour = Integer.parseInt(masTime[0]);
//        int myMinute = Integer.parseInt(masTime[1]);
//        int mySecond = Integer.parseInt(masTime[2]);
//        LocalTime time = LocalTime.of(myHour, myMinute, mySecond);
        LocalTime time = LocalTime.of(17, 30, 40);
        LocalDateTime myTime = myDate.atTime(time);
        LocalDateTime nowTime = LocalDateTime.now();
        DateTimeFormatter formatMyTime = DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm:ss");
        System.out.println("Твой день рождения (вариант с LocalDateTime) : " + Color.GREEN + formatMyTime.format(myTime) + Color.DEFAULT);
        System.out.println("Дата сегодня                                 : " + Color.GREEN + formatMyTime.format(nowTime) + Color.DEFAULT);


        long timeStart4 = System.currentTimeMillis();
        System.out.println(Color.BLUE + "Вам исполнилось (вариант с LocalDateTime) 1-й метод : " + Color.YELLOW + getDifferenceFromToday(myTime)[0] + " лет, " + getDifferenceFromToday(myTime)[1] + " месяца, " + getDifferenceFromToday(myTime)[2] + " дней, " + getDifferenceFromToday(myTime)[3] + " часа, " + getDifferenceFromToday(myTime)[4] + " минут и " + getDifferenceFromToday(myTime)[5] + " секунд" + Color.DEFAULT);
        System.out.println("Длительность расчетов (вариант с LocalDateTime) = " + (System.currentTimeMillis() - timeStart4) + " мил.сек.");


        long timeStart5 = System.currentTimeMillis();
        System.out.println(Color.BLUE + "Вам исполнилось (вариант с LocalDateTime) 2-й метод : " + Color.YELLOW + getBetweenTwoDates(myTime, nowTime)[0] + " лет, " + getBetweenTwoDates(myTime, nowTime)[1] + " месяца, " + getBetweenTwoDates(myTime, nowTime)[2] + " дней, " + getBetweenTwoDates(myTime, nowTime)[3] + " часа, " + getBetweenTwoDates(myTime, nowTime)[4] + " минут и " + getBetweenTwoDates(myTime, nowTime)[5] + " секунд" + Color.DEFAULT);
        System.out.println("Длительность расчетов ChronoUnit.HOURS.between = " + (System.currentTimeMillis() - timeStart5) + " мил.сек.");
//        scanner.close();
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
     * метод getDifferenceFromToday : определяет, сколько времени прошло с полученной даты в метод и по дату сегодня
     * т.е. LocalDate.now(). Метод принимает переменную типа LocalDate и возвращает int[3] в
     * котором int[0] разница лет, int[1] разница месяцев, int[2] разница дней
     */
    private static int[] getDifferenceFromToday(LocalDate fromDate) {
        int[] between = new int[3];
        LocalDate today = LocalDate.now();
        int deltaTime;
        if (fromDate.getDayOfMonth() > today.getDayOfMonth()) {
            between[2] = LocalDate.now().minusMonths(1).lengthOfMonth() - fromDate.getDayOfMonth() + today.getDayOfMonth();
            deltaTime = 1;
        } else {
            between[2] = today.getDayOfMonth() - fromDate.getDayOfMonth();
            deltaTime = 0;
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

    /**
     * метод getDifferenceFromToday перегружен переменной типа LocalDateTime: определяет, сколько времени прошло
     * с полученной даты в метод и по дату сегодня т.е. LocalDateTime.now(). Метод принимает переменную
     * типа LocalDateTime и возвращает int[6] в котором
     * int[0] разница лет, int[1] разница месяцев, int[2] разница дней, int[3] разница часов, int[4] разница минут,
     * int[6] разница секунд
     */

    private static int[] getDifferenceFromToday(LocalDateTime fromDate) {
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
