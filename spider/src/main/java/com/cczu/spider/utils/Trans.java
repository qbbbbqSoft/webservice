package com.cczu.spider.utils;


class Trans {
    public static void transpose()
    {
        int  a[][]={{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("转置前：");
        for(int i=0; i<=2;i++)
        { for(int j=0;j<=2;j++ )
        { System.out.print(a[i][j]+"("+i+j+")\t");}
            System.out.println();
        }
        //转置
        for(int i=0; i<=2;i++)
        { for(int j=0;j<=i;j++ )//注意只能遍历一半，所以j<=i如果全部遍历，则数据交换了两次,相当于没有置换
        {
            int temp;
            temp=a[i][j];
            a[i][j]=a[j][i];
            a[j][i]=temp;
        }
        }
        System.out.println("转置后");
        for(int i=0; i<=2;i++)
        { for(int j=0;j<=2;j++ )
        { System.out.print(a[i][j]+"("+i+j+")\t");}
            System.out.println();
        }
    }

    public static void main(String[] args) {
        transpose();
    }

}

