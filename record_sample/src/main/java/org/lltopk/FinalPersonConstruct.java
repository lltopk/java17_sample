package org.lltopk;

public record FinalPersonConstruct(String name, int age, String email) {

    // 1. 紧凑构造函数：用于验证或规范化数据
    // 注意：这里没有参数列表，逻辑写在方法体里
    // 这个构造函数会在主构造函数赋值之前执行
    public FinalPersonConstruct {
        // 验证逻辑
        if (age < 0) {
            throw new IllegalArgumentException("年龄不能为负数");
        }
        // 规范化逻辑：确保邮箱不为空
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("邮箱不能为空");
        }
        // 注意：不需要写 this.name = name; 这种赋值语句，
        // 编译器会自动在最后帮你加上，你只需要在上面做校验或修改即可。
    }

    // 2. 额外的构造函数, 第一行必须使用this(...)委托给规范构造函数, 
    // 场景：提供默认值
    public FinalPersonConstruct(String name, int age) {
        this(name, age, name.toLowerCase() + "@default.com"); // 委托给主构造函数
    }

    public FinalPersonConstruct(String name) {
        this(name, 18); // 委托给主构造函数
    }

    public FinalPersonConstruct(int age) {
        this("default user", age); // 委托给主构造函数
    }

    // 静态工厂方法：从字符串创建
    public static FinalPersonConstruct fromStringTemplate(String str) {
        String[] parts = str.split("-");
        String name = parts[0];
        int age = Integer.parseInt(parts[1]);
        String email = parts[2];
        return new FinalPersonConstruct(name, age, email);
    }

    // 静态工厂方法：of
    public static FinalPersonConstruct of(int age) {
        return new FinalPersonConstruct(age);
    }

    public static void main(String[] args) {
        // 使用全参构造
        FinalPersonConstruct p1 = new FinalPersonConstruct("Alice", 30, "alice@gmail.com");
        System.out.println(p1);
        // 使用自定义的简略构造（只有姓名和年龄）此时 email 会被自动设置为 "bob@default.com"
        FinalPersonConstruct p2 = new FinalPersonConstruct("Bob", 25);
        System.out.println(p2);
        // 使用自定义的简略构造（只有姓名和年龄）此时 name 会被自动设置为 "default user"
        FinalPersonConstruct p3 = new FinalPersonConstruct(25);
        System.out.println(p3);
        // 如果传入非法参数，会抛出异常
//        FinalPersonConstruct p4 = new FinalPersonConstruct("Charlie", -5); // 抛出 IllegalArgumentException


        // 使用静态工厂方法
        FinalPersonConstruct r1 = FinalPersonConstruct.fromStringTemplate("lltopk-18-123@123.com");
        System.out.println(r1);
        FinalPersonConstruct r2 = FinalPersonConstruct.of(5);
        System.out.println(r2);
    }
}

