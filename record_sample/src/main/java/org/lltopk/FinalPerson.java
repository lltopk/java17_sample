package org.lltopk;

/**
 * 使用record定义的是不变类；并且字段也不可变：无论你怎么构造，一旦创建，record 的字段（如 name, age）就是 final 的，无法修改。
 *
 * 因此适用于定义网络传输实体类
 * @param name
 * @param age
 * @param email
 */
public record FinalPerson(String name, int age, String email) {
    
    public FinalPerson(){
        this("default user",18,"123@123.com");
    }
    
    public static void main(String[] args) {
        FinalPerson finalPerson = new FinalPerson("zs", 18, "www@123.com");
        System.out.println(finalPerson);
        System.out.println(finalPerson.name);
//        finalPerson.age = 20;//Cannot assign a value to final variable 'age'

        FinalPerson finalPerson2 = new FinalPerson();
        System.out.println(finalPerson2.name);
//        finalPerson2.email = "456@456.com";//Cannot assign a value to final variable 'email'
    }
}

