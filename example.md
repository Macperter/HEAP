>  如何使用 <br>  
```java
    Heap<Integer> q = new Heap<>(new cmp(), Heap.GREATER);//排序方法
        Scanner obj = new Scanner(System.in);
        int a = obj.nextInt();
        while (a != -88) {
            q.push(a);
            a = obj.nextInt();
        }
        obj.close();
        while (!q.Empty()) {
            System.out.println(q.top());
            q.pop();
        }
```

