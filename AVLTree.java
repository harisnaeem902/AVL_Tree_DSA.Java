public class AVLTree {

        class Node{
            int data;
            Node left;
            Node right;
            int height;

        }
        Node root=null;

        private int height(Node n){
            if(n==null){
                return 0;
            }
            else{
                return n.height;
            }
        }
        private int balanceFactor(Node n){
            if(n==null){
                return 0;
            }
            else{
                return height(n.left)-height(n.right);
            }
        }

        int maxHeight(int a,int b){
            if (a>b){
                return a;
            }else{
                return b;
            }
        }

        private Node leftRotate(Node n){
            Node t =n.right;
            Node t2=t.left;
            t.left=n;
            n.right=t2;
            n.height=maxHeight(height(n.left),height(n.right))+1;
            t.height=maxHeight(height(t.left),height(t.right))+1;
            return t;
        }

        private Node rightRotate(Node n){
            Node t =n.left;
            Node t2=t.right;
            t.right=n;
            n.left=t2;
            n.height=maxHeight(height(n.left),height(n.right))+1;
            t.height=maxHeight(height(t.left),height(t.right))+1;
            return t;
        }
        //    Traverse For printing Values
        public void preOrder(){
            preOrder(root);
        }
        private void preOrder(Node n)
        {
            if(n!=null){
                System.out.print(n.data + " ");
                preOrder(n.left);
                preOrder(n.right);

            }
        }

        public void inOrder(){
            inOrder(root);
        }
        private void inOrder(Node n)
        {

            if(n!=null){
                inOrder(n.left);
                System.out.print(n.data + " ");
                inOrder(n.right);
            }
        }

        public void postOrder(){
            postOrder(root);
        }

        private void postOrder(Node n)
        {
            if(n!=null){
                postOrder(n.left);
                postOrder(n.right);
                System.out.print(n.data+" ");

            }

        }
        //    Insert


        public void Insert(int value)
        {
            root=insertValue(root,value);
        }
        private Node insertValue(Node root, int value){
            if (root == null) {
                Node t =new Node();
                t.data=value;
                root=t;
                return root;
            } else if (value<root.data){
                root.left=insertValue(root.left,value);
            }
            else if (value>root.data){
                root.right=insertValue(root.right,value);
            }else
                return root;

            root.height=maxHeight(height(root.left),height(root.right))+1;
            int bf=balanceFactor(root);

            if (bf>1){
//                RightRotate
                if(value<root.left.data){
                    return rightRotate(root);
                }
//                Right Left Rotate
                else if (value>root.left.data) {
                    root.left=leftRotate(root.left);
                    return rightRotate(root);
                }
            }
            if (bf<-1){
//                LeftRotate
                if (value> root.right.data){
                    return leftRotate(root);
                }
//                LeftRight
                else if (value<root.right.data) {
                    root.right=rightRotate(root.right);
                    return leftRotate(root);
                }
            }
            return root;
        }


        //      Delete


//      Search

        public void Search(int key){
            Search(key,root);
        }
        private Node Search(int key, Node n){
            //Node t = null;
            //t.data=key;
            if(n!=null){
                if(n.data==key){
                    return n;
                }
                else{

                    if(key > n.data){
                        Search(key,n.left);
                    }
                    if(key < n.data){
                        Search(key,n.left);
                    }

                }
            }

            return null;
        }

        public boolean Search2(int key){
            return Search2(key,root);

        }

        private boolean Search2(int key, Node n) {
            //Node t=null;
            //t.data = key;
            if (n == null) {
                return false;

            } else {

                if (n.data == key) {
                    return true;
                } else if (key > n.data) {
                    return Search2(key, n.right);
                } else {
                    return Search2(key, n.left);
                }
            }


        }
//          Delete
        public void Delete(int key){
            root=Delete(key,root);
        }

        private Node Delete(int key, Node root) {

            if (root == null) {
                System.out.println("Not Found");

            } else if (key < root.data){
                root.left= Delete(key,root.left);
            } else if (key > root.data) {
                root.right=Delete(key,root.right);
            }
            else {
                if(root.left==null){
                    return root.right;
                } else if (root.right==null) {
                    return root.left;
                }
                else{
                    root.data=Successor(root.right);
                    root.right=Delete(root.data,root.right);
                }
            }

            if (root==null){
                return root;
            }
                    root.height=maxHeight(height(root.left),height(root.right))+1;
                    int bf=balanceFactor(root);

                    if (bf>1){
//                RightRotate
                        if(balanceFactor(root.left)>=0){
                            return rightRotate(root);
                        }
//                Right Left Rotate
                        else if (balanceFactor(root.left)<0) {
                            root.left=leftRotate(root.left);
                            return rightRotate(root);
                        }
                    }
                    if (bf<-1){
//                LeftRotate
                        if (balanceFactor(root.right)<=0){
                            return leftRotate(root);
                        }
//                LeftRight
                        else if (balanceFactor(root.right)>0) {
                            root.right=rightRotate(root.right);
                            return leftRotate(root);
                        }
                    }
                    return root;


        }

//        Successor
        private int Successor(Node n){
            int successor=n.data;
            Node t =n;
            while(n.left!=null){
                successor=t.left.data;
                t=t.left;
            }
            return  successor;
        }
    }





