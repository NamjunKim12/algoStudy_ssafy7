import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    static class Node {
        Node left, right;
        int data;

        Node(int data) {
            this.data = data;
        }
    }

    static Node root;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 루트 받기.
        root = new Node(sc.nextInt());

        while (sc.hasNext()) {
            // 값을 하나 받아온다.
            add(root, sc.nextInt());
        }

        postord(root);

        System.out.println(ans);

        sc.close();
    }

    public static void add(Node curr, int data) {
        if (curr.data < data) {
            if (curr.right == null) {
                curr.right = new Node(data);
                return;
            }
            add(curr.right, data);
        }
        else {
            if (curr.left == null) {
                curr.left = new Node(data);
                return;
            }
            add(curr.left, data);
        }
    }

    public static void postord(Node curr) {
        if (curr.left != null) postord(curr.left);
        if (curr.right != null) postord(curr.right);
        ans.append(curr.data);
        ans.append('\n');
    }
}
