import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int n = 5; //Длина вектора
        //создание объектов типа вектор
        Vector vec = new Vector(n);
        Vector vec2 = new Vector(n);
        Vector vecSum = new Vector(n);
        Vector vecMult = new Vector(n);



        //Заполнение случайными значениями два вектора
        vec.fillRandNum();
        vec2.fillRandNum();

        //Вывод в консоль первого вектора
        System.out.println("Первый вектор:");
        vec.view();
		System.out.println("Длина вектора: " + vec.length());
		System.out.println("Минимальный элемент вектора: " + vec.min());
		System.out.println("Максимальный элемент вектора: " + vec.max());
        System.out.println("Евклидова норма: " + vec.euclidNorm());
        System.out.print("Вектор умноженный на 2: ");
        vecMult.mult(vec,2);
        vecMult.view();
        System.out.println();

        //Вывод в консоль второго вектора
        System.out.println("Второй вектор:");
        vec2.view();
		System.out.println("Длина вектора: " + vec2.length());
		System.out.println("Минимальный элемент вектора: " + vec2.min());
		System.out.println("Максимальный элемент вектора: " + vec2.max());
        System.out.println("Евклидова норма: " + vec2.euclidNorm());
        System.out.print("Вектор умноженный на 6: ");
        vecMult.mult(vec2,6);
        vecMult.view();
        System.out.println();

        //Сумма векторов
        System.out.println("Сумма векторов:");
        vecSum.vecSum(vec, vec2);
        vecSum.view();

        //Отсортированный вектор
        System.out.println("Отсортированный вектор суммы первого и второго векторов:");
        vecSum.sort();
        vec.view();

        //Скалярное произведение векторов
        System.out.println("Скалярное произведение первого и второго векторов:");
        System.out.println(vec.scalarMult(vec, vec2));

    }
}
class Vector {
    Random rand = new Random();
    private final int length;
    public int[] vec;

    Vector(int length){
    this.length=length;
    vec = new int[length];
    }
    void view(){
        for(int i = 0; i < length; i++)
            System.out.print(vec[i] + " ");
        System.out.println();
    }
    void add(int ind, int num){ //Добавление/редактирование элемента вектора
        vec[ind] = num;
    }
    int elem(int ind){ //Вывод элемента
        return vec[ind];
    }
    int length(){ //Вывод длины вектора
        return length;
    }
    void fillRandNum(){ //Заполнение случайными числами
        for(int i = 0; i < vec.length; i++) { vec[i] = rand.nextInt(50); }
    }
    int min(){ //Минимальное число из вектора
        int minnum = vec[0];
        for(int i = 0; i < vec.length; i++){
            if (minnum > vec[i])
                minnum = vec[i];
        }
        return minnum;
    }
    int max(){ //Максимальное число из вектора
        int maxnum = vec[0];
        for(int i = 0; i < vec.length; i++){
            if (maxnum < vec[i])
                maxnum = vec[i];
        }
        return maxnum;
    }
    void sort(){ //Сортировка по убыванию
        int buff;
        for(int j=0; j<vec.length-1; j++){
            if (vec[j] < vec[j+1]){
                buff = vec[j];
                vec[j] = vec[j+1];
                vec[j+1]= buff;
                j=-1;
            }
        }
    }
    double euclidNorm(){//евклидовая норма
        double euc=0;
        for(int i = 0; i<vec.length; i++)
            euc+=vec[i]*vec[i];
        euc = Math.sqrt(euc);
        return euc;
    }
    void mult(Vector vect, int multNum){
        for(int i = 0; i < vec.length; i++)
            add(i, vect.elem(i)*multNum);
    }
    void vecSum(Vector vec, Vector vec2){
        for(int i = 0; i<vec.length();i++)
            add(i, vec.vec[i] + vec2.vec[i]);
    }
    int scalarMult(Vector vec, Vector vec2){
        int scalarMult = 0;
        for(int i = 0; i<vec.length();i++)
            scalarMult += vec.vec[i] * vec2.vec[i];
        return scalarMult;
    }

}