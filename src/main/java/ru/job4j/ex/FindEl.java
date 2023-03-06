package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (key.equals(value[i])) {
                rsl = i;
                break;
            }
        }
        if (rsl == -1) {
            throw new ElementNotFoundException("element is missing from the array");
        }
        return rsl;
    }

    public static void main(String[] args) {
        String[] strings = new String[] {"test", "item", "other", "new"};
        try {
            int indexString = FindEl.indexOf(strings, "newItem");
            System.out.println(indexString);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

    }
}