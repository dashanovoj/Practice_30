/* 1. Сначала создать бинарное поисковое действие из 10 узлов, затем
содержимое дерева распечатать
2. из него удалить один узел.
3. После этого выполняется еще одна распечатка, чтобы проверить,
как прошло удаление
4. дерево удаляется уже полностью с освобождением памяти. */

// Определяем класс для узла дерева
class TreeNode {
    // Поля данных
    int value; // хранит значение узла
    TreeNode left; // ссылка на левого потомка
    TreeNode right; // ссылка на правого потомка

    /*  который инициализирует узел
    с заданным значением и устанавливает ссылки на потомков в null */
    TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

// Определяем класс для бинарного поискового дерева
class BinarySearchTree {
    // Поля данных
    private TreeNode root; // корень дерева (начальный узел)

    // Метод для вставки узла (вызывает рекурсивный метод insertRec для инкапсуляции)
    public void insert(int value) {
        root = insertRec(root, value); // вставляем новое значение в дерево
    }

    // Рекурсивный метод, который находит правильное место для вставки нового узла
    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) { // если текущий узел пуст
            root = new TreeNode(value); // создаём новый узел с заданным значением
            return root;
        }
        /* Если значение меньше, чем значение текущего узла,
        рекурсивно вызываем insertRec для левого поддерева */
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        }
        /* Если значение больше, чем значение текущего узла,
        рекурсивно вызываем insertRec для правого поддерева */
        else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root; // возвращаем текущий узел
    }

    // Метод для удаления узла (вызывает рекурсивный метод deleteRec для инкапсуляции)
    public void delete(int value) {
        root = deleteRec(root, value);
    }

    // Рекурсивный метод, который ищет и удаляет узел
    private TreeNode deleteRec(TreeNode root, int value) {
        // Если узел пуст, возвращаем null
        if (root == null) return root;

        // Если значение меньше, рекурсивно ищем в левом поддереве
        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        }

        // Если значение больше, рекурсивно ищем в правом поддереве
        else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        }
        // Нашли узел для удаления
        else {
            // Узел с одним или без дочерних узлов
            // Если у узла нет левого потомка, возвращаем правого
            if (root.left == null) return root.right;
            // Если у узла нет правого потомка, возвращаем левого
            else if (root.right == null) return root.left;

            // Узел с двумя дочерними узлами
            /* Если у узла два потомка, находим минимальное значение
            в правом поддереве и заменяем значение текущего узла */
            root.value = minValue(root.right);
            root.right = deleteRec(root.right, root.value);
        }
        return root;
    }

    // Метод для нахождения минимального значения в поддереве
    private int minValue(TreeNode root) {
        //  Ищем самый левый узел (минимальное значение)
        int minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    // Метод для печати дерева в виде структуры
    public void printTree() {
        printTree(root, 0);
    }

    // Вспомогательный метод для рекурсивной печати дерева
    private void printTree(TreeNode node, int space) {
        if (node == null) return;

        // Увеличиваем отступ
        space += 10;

        // Сначала печатаем правое поддерево
        printTree(node.right, space);

        // Печатаем текущий узел после отступов
        System.out.print("\n");
        for (int i = 10; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(node.value + "\n");

        // Печатаем левое поддерево
        printTree(node.left, space);
    }

    // Метод для освобождения памяти
    public void clear() {
        root = null;
    }
}

public class Task1_4 {
    public static void main(String[] args) {
        // Создаём экземпляр бинарного поискового дерева
        BinarySearchTree bst = new BinarySearchTree();

        // 1. Создаем бинарное поисковое дерево из 10 узлов
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 90, 5};
        for (int value : values) {
            bst.insert(value);
        }

        // и печатаем содержимое дерева
        System.out.println("Содержимое дерева (в порядке возрастания):");
        bst.printTree();

        // 2. Удаляем один узел (например, 70)
        bst.delete(70);

        // 3. Печатаем содержимое дерева после удаления
        System.out.println("Содержимое дерева после удаления 70:");
        bst.printTree();

        // 4. Удаляем дерево и освобождаем память
        bst.clear();
        System.out.println("Дерево очищено.");
    }
}
