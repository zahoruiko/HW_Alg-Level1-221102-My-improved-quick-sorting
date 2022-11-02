import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Определен массив
        int[] sourceArray = {70, 250, 50, 300, 1};
        System.out.println("Исходный массив: ");
        // Выводим сидержимое исходного массива
        System.out.println(Arrays.toString(sourceArray));
        // Получаем минимальное и максимальное значения в массиве
        Map<String, Integer> result = getMinMax(sourceArray);
        // Отображаем мнинимальное и максимальное значения
        System.out.println("Min = " + result.get("min"));
        System.out.println("Max = " + result.get("max"));
    }

    public static Map<String, Integer> getMinMax(int[] sourceArray) {
        // Определяем переменную-контейнер для возврата результата
        Map<String, Integer> results = new HashMap<>();
        // Сортируем массив методом быстрой сортировки с использованием рекурсии
        quickSort(sourceArray, 0, sourceArray.length - 1);
        // Помещаем минимальное значение в контейнер
        results.put("min", sourceArray[0]);
        // Помещаем максимальное значение в контейнер
        results.put("max", sourceArray[sourceArray.length - 1]);
        // возвращаем результат
        return results;
    }

    public static void quickSort(int[] array, int lowIndex, int highIndex) {
        // Если массив пустой, то выходим
        if (array.length == 0)
            return;

        // Нижний индекс должен быть меньше верхнего, если равен или больше, тогда выходим
        if (lowIndex >= highIndex)
            return;

        // Определяем индекс опорного элемента
        int middleIndex = lowIndex + (highIndex - lowIndex) / 2;
        // Значение опорного элемента
        int middleValue = array[middleIndex];

        // Устанавливаем начальные значения индексов для проверки элементов справа и слева от опорного элемента
        int lowIndexCheck = lowIndex, highIndexCheck = highIndex;
        // Продолжаем перебор массива пока проверяемый нижний индекс меньше или равен проверяемому верхнему индексу
        // Выходим из цикла, если проверяемый нижний индекс больше проверяемого верхнего
        while (lowIndexCheck <= highIndexCheck) {
            // С левой стороны от опорного элемента (последовательным перебором индекса: от нуля в большую сторону)
            //  находим элемент (и его индекс), который равен опорному элементу или больше него (движение слева направо)
            while (middleValue > array[lowIndexCheck]) {
                // Увеличиваем значение индекса, чтобы проверить следующий за ним элемент
                // (движемся вправо - приближаемся к опорному элементу)
                lowIndexCheck++;
            }
            // С правой стороны от опорного ищем элемент /**/элемент который равен опорному или меньше него
            // (движение справо налево: значение индекса уменьшается от длина массива -1 ),
            while (array[highIndexCheck] > middleValue) {
                // Увеличиваем значение индекса, чтобы проверить следующий за ним элемент
                // (движемся влево - приближаемся к опорному элементу)
                highIndexCheck--;
            }

            // Если нижний проверяемый индекс меньше верхнего проверяемого индекса,
            // то меняем позиции этих элементов местами (взаимно меняем их индексы между ними)
            if (lowIndexCheck <= highIndexCheck) {
                if (array[lowIndexCheck] != array[highIndexCheck] && // Переставляем только элементы с разными значениями (могут быть и одинковые)!
                    lowIndexCheck != highIndexCheck ) { // Переставлять одно и то же значение - тоже бесполезно обрабатывать (чтобы не переставлять одно и тот же элемент из своей позиции в свою же)!
                    // сохраняем значение элемента из "левой" части массива (с "нижним" индексом) во временной переменной
                    int temp = array[lowIndexCheck];
                    // присваиваем эначению с "нижним" индексом значение с "верхним" индексом (из "правой" части массива)
                    array[lowIndexCheck] = array[highIndexCheck];
                    // присваиваем значению с "верхним" индексом значение с "нижним" индексом (из временной переменной)
                    array[highIndexCheck] = temp;
                }
                // Даже если элементы массива и не переставлялись - необходимо изменить значения следующих переменных (индексов)
                // Переходим к элементу со следующим индексом (движемся вправо)
                lowIndexCheck++;
                // Переходим к элементу со следующим индексом (движемся влево)
                highIndexCheck--;
            }
        }

        // вызовы рекурсии для сортировки левой и правой части
        // Если нижний индекс меньше верхнего проверяемого индекс,
        // то продолжаем сортировку и устанавливаем новые границы:
        // Если нижний индекс равен верхнему проверяемому, то делать нечего
        if (lowIndex < highIndexCheck) {
            quickSort(array, lowIndex, highIndexCheck);
        }

        // Если нижний проверяемый индекс меньше правой границы
        if (lowIndexCheck < highIndex) {
            quickSort(array, lowIndexCheck, highIndex);
        }
    }
}
