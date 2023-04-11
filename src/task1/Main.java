package task1;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger butif3 = new AtomicInteger(0);
    static AtomicInteger butif4 = new AtomicInteger(0);
    static AtomicInteger butif5 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        Thread thread1 = new Thread(() -> isPalindrome(texts));
        Thread thread2 = new Thread(() -> isAAA(texts));
        Thread thread3 = new Thread(() -> sortNick(texts));
        thread1.start();
        thread2.start();
        thread3.start();
        thread3.join();
        thread2.join();
        thread1.join();
        System.out.println("Красивый слов с длиной 3: " + butif3.get() + " шт.");
        System.out.println("Красивый слов с длиной 4: " + butif4.get() + " шт.");
        System.out.println("Красивый слов с длиной 5: " + butif5.get() + " шт.");
    }

    public static void isPalindrome(String[] mas) {
        for (int i = 0; i < mas.length - 1; i++) {
            if (mas[i].equals(new StringBuilder(mas[i]).reverse().toString())) {
                func(mas[i]);
            }
        }
    }

    public static void isAAA(String[] mas) {
        s:
        for (int i = 0; i < mas.length - 1; i++) {
            String mes = mas[i];
            for (int j = 0; j < mes.length() - 1; j++) {
                if (mes.charAt(j) != mes.charAt(j + 1)) continue s;
            }
            func(mes);
        }
    }

    public static void sortNick(String[] mas) {
        s:
        for (int i = 0; i < mas.length - 1; i++) {
            String mes = mas[i];
            for (int j = 0; j < mes.length() - 1; j++) {
                if (mes.charAt(j) > mes.charAt(j + 1)) continue s;
            }
            func(mes);
        }
    }

    public static void func(String mes) {
        switch (mes.length()) {
            case 3 -> butif3.addAndGet(1);
            case 4 -> butif4.addAndGet(1);
            case 5 -> butif5.addAndGet(1);
        }
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
