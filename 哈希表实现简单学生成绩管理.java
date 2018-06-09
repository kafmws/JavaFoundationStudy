class Student {
    private HashMap<String, HashMap<String, Integer>> stu = new HashMap<String, HashMap<String, Integer>>();

    public void add(String name) {
        HashMap<String, Integer> t = new HashMap<String, Integer>();
        if(stu.containsKey(name)){
            System.out.println("该学生已存在！");return;
        }
        stu.put(name,t);
    }

    public void addscore(String name, String subject, int score) {
        if(stu.containsKey(name))
        stu.get(name).put(subject,score);
        else{
            System.out.println("该学生不存在！");
        }
    }
    public void search(String name ,String subject){
        if(stu.containsKey(name))
        stu.get(name).get(subject);
        else {
            System.out.println("该学生不存在！");
        }
    }
    public void print(String name){
        HashMap<String,Integer> t=stu.get(name);
        for (String s:t.keySet()) {
            System.out.println(s+" : "+t.get(s));
        }
    }
    public void remove(String name){
        stu.remove(name);
    }
    public void removescore(String name ,String subject){
        stu.get(name).remove(subject);
    }
}

public class Main {
    public static void show(){
        System.out.println("1.增加学生");
        System.out.println("2.删除学生");
        System.out.println("3.添加/修改学生成绩");
        System.out.println("4.删除学生成绩");
        System.out.println("5.查询学生成绩");
        System.out.println("0.退出");
    }
    public static void main(String[] args) {
        Student stu = new Student();
        Scanner input =new Scanner(System.in);
        while (true){
            show();
            switch (input.nextInt()){
                case 1:System.out.println("请输入学生姓名:");stu.add(input.next());break;
                case 2:System.out.println("请输入学生姓名:");stu.remove(input.next());break;
                case 3:System.out.println("请输入学生姓名、科目名称、成绩:");stu.addscore(input.next(),input.next(),input.nextInt());break;
                case 4:System.out.println("请输入学生姓名、科目名称:");stu.removescore(input.next(),input.next());break;
                case 5:System.out.println("请输入学生姓名:");stu.print(input.next());break;
            }
        }
    }
}