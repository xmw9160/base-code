package com.xmw.base;

//12月22日公开课
//算法设计
//预备知识：
//1.分支语句if...    if...else...
//2.循环语句for      while
//3.异或运算符
//4.数组
class BaseDemo {
    public static void main(String[] args) {
        //异或运算符 ^
        //System.out.println(1^2);
        //System.out.println(34^17^17);
        //System.out.println(15^1^2^3^3^2^1);

        //算法
        //计算的方法：在进行某种运算时所使用的方法/方式
        //计算机的算法：在计算机中进行运算时为计算机提前设定的运算方式
        //关键指标
        //1.运行时间
        //2.内存消耗
		/*
		最终结论：牺牲内存，换取时间
								牺牲时间，换取内存
		拿时间换空间，拿空间换时间						
		*/
				
		/*
		题设：现有0到99，共计100个整数，各不相同，将所有数放入一个
					数组，随机排布。数组长度101,多余的数字是0到99其中任
					意一个数(唯一重复的数字)
		问题：将这个重复的数字找出来
		*/
        //创建了一个101个整数的空间
        //初始化了一个空间为101的数组，并对其进行了数据初始化
		/*
		int[] arr = new int[101];
		for(int i = 0;i<=99;i++){
			arr[i] = i;
		}
		arr[100] = 69;
		
		for(int x = 1;x<=10000;x++){
			//随机排列
			int num1 = (int)(Math.random()*101);
			int num2 = (int)(Math.random()*101);
			//arr[num1]		arr[num2] 两个数据互换位置
			int temp = arr[num1];
			arr[num1] = arr[num2];
			arr[num2] = temp;
		}
		*/
        //--------------以上将题目设定出来------------------
        //问题：将这个重复的数字找出来
        //解决方案一：
		/*
		itcast:
		for(int i = 0;i<arr.length;i++){
			//取出第一个数字，然后与后面的所有数字比对
			//arr[i]与后面所有的数字比  arr[i+1]向后的数字
			for(int j =i+1;j<arr.length;j++){
				if(arr[i]==arr[j]){
					System.out.println("找到了重复的数字"+arr[i]);
					break itcast;
				}
			}
		}
		*/


        //解决方案二：
        //0到99 ，其中有一个重复的数字
        //将数组中所有的数字加一起0到99的和+重复的那个数字
        //将上述结果减去0到99的和  就可以得到重复的数字了
		/*
		int sum = 0;
		//将数组中所有的数字都加一起
		for(int i = 0;i<arr.length;i++){
			sum+=arr[i];
		}
		//使用上面的结果减0到99
		for(int i = 0;i<=99;i++){
			sum-=i;
		}
		System.out.println(sum);
		//方案二如果计算的数字特别多，比如有3万亿个数据，超出了数据类型
		*/
		
		/*
		//解决方案三：
		//0^1^2^3..^m...^99^m  ^0^1^2^3.....^99 = x
		//使用数组中的第一个数据，异或后面的所有数据
		for(int i = 1;i<arr.length;i++){
			arr[0]=arr[0]^arr[i];
		}
		for(int i = 0;i<=99;i++){
			arr[0]=arr[0]^i;
		}
		System.out.println(arr[0]);
		*/

        //0-99里面有一个数字是重复的，一共101个数字
        //0-99里面去掉一个，随机去掉一个，替换成另一个0-99的数字
		
		/*
		题设：现有0到99，共计100个整数，各不相同，将所有数放入一
					个数组，随机排布。数组长度100，将其中任意一个数替换
					成0到99另一个数(唯一重复的数字)
		问题：将这个重复的数字找出来
		*/
		/*
		int[] arr = new int[100];
		for(int i =0;i<arr.length;i++){
			arr[i] = i;
		}
		//打乱数组的排列顺序
		for(int x = 1;x<=10000;x++){
			//随机排列
			int num1 = (int)(Math.random()*100);
			int num2 = (int)(Math.random()*100);
			//arr[num1]		arr[num2] 两个数据互换位置
			int temp = arr[num1];
			arr[num1] = arr[num2];
			arr[num2] = temp;
		}
		//将其中一个替换成另一个
		int n1 = (int)(Math.random()*100);
		int n2 = (int)(Math.random()*100);
		if(n1 == n2){
			n1 = (int)(Math.random()*100);
		}
		//将随机的n2位置的值换成n1位置的值
		//System.out.println(n1+"位置的值换成了"+n2+"位置的值");
		arr[n1] = arr[n2];
		
		
		for(int i = 0;i<arr.length;i++){
			System.out.print(arr[i]+"\t");
		}
		System.out.println("--------------");
		//---------------题目构造完成------------------
		//使用上面的方案一可以解决这个问题
		//算法：
		//1.创建一个辅助的数组空间,里面的数据元素全是0
		//*****在解决方案中使用了全新的数组，用于赋值解决问题
		//*****这个数组是需要消耗内存空间的，但是由于它的出现
		//*****加速了重复数据的查找速度，因此速度提高了，但是
		//*****内存被浪费了一些
		int[] newArr = new int[100];
		
		//2.循环原始数组，使用里面的数据做为新数组的下标，进行自加
		for(int i = 0;i<arr.length;i++){
			newArr[arr[i]]++;
			if(newArr[arr[i]] == 2){
				//当前下标对应的数据就是你要找的数据
				System.out.println(arr[i]);
				break;
			}
		}
		*/


        //题设：某门户网站，具有如下业务功能
        //			客户输入个人信息时，当输入年龄，会根据输入的年龄值
        //			显示其所属年龄段
		/*			 0 ～  9	儿童
						10 ～ 19	少年
						20 ～ 29	青年
						30 ～ 39	青壮年
						40 ～ 49	壮年
						50 ～ 59	中年
						60 ～ 69	中老年
						70 ～ 79	老年
						80 ～ 89	老老年
						90 ～ 99	老老老年									*/
        //问题：上述业务日均访问量超百万次，设计完成上述功能的程序
        //百万次访问次数多，要求速度最快，一定是速度优先，时间尽量少
		/*
		int age = 37;
		if(0-9){
			System.out.println(.....);
		}else if(10-19){
			System.out.println(.....);
		}else if(age.....20  29){
			System.out.println(.....);
		}else if(age.....30  39 ）{
			System.out.println(.....);
		}else if(age.....40  49){
			System.out.println(.....);
		}else if(age.....){
			System.out.println(.....);
		}else if(age.....){
			System.out.println(.....);
		}else if(age.....){
			System.out.println(.....);
		}else if(age.....90 99){
			System.out.println(.....);
		}
		
		0-39  40-99
		
		switch
		*/
		
		/*
		int age = 17;
		//题目的要求，按照年龄段来进行划分
		//age/10 ->得到这个人是哪个年龄段的
		String[] ages = {"儿童","少年","青少年","青年","青壮年","壮年","aaa","bbb","ccc","ddd"};
		System.out.println(ages[age/10]);
		//一次判断都没有，做了一次除法，还有一次获取数组元素的计算
		//声明了一个数组，数组要占用空间，辅助解决当前问题
		//拿空间换时间
		*/


        //问题：计算求出1到100的所有质数
        //质数：只能被1和其本身整除的数字叫做质数
        //质数：从2开始到n-1都不能整除这个数字n
		/*
		itcast:
		for(int i = 2;i<=100000000;i++){
			//如果判断了这个数字是质数，打印这个数字，否则不打印
			//构造反复进行整除判定的循环
			
			//第一次优化：j值不需要进行到i-1
			//第二次优化：j值比需要进行到i/2，进行到i的平方根就OK了
			for(int j = 2;j<Math.sqrt(i);j++){
				//System.out.println("验证数字"+i+"是否能被"+j+"整除");
				if(i % j == 0){
					//整除了，不需要继续计算了，也不需要打印了，进行下一个数字
					continue itcast;
				}
			}
			System.out.print(i+"\t");
		}
		*/


        //问题：计算求出1到10000的所有质数
        //问题：计算求出1到100000000的所有质数
        //思路：
        //1.每次计算出一个质数后，将这个质数保存起来，存到数组中
        //2.每次验证都从保存的地方获取数据
        //用于保存所有的质数的数组
		/*
		int[] nums = new int[16];
		//声明一个变量，记录当前数组中已经有几个质数了
		int num = 0;
		
		
		//计算当前的时间
		long start = System.currentTimeMillis();
		itcast:
		for(int i = 2;i<=1000000;i++){
			
			for(int j = 2;j<=Math.sqrt(i);j++){
				if(i%j == 0){
					continue itcast;
				}
			}
			
			
			//从数组中第一个数据开始使用
			for(int j = 0;j < num;j++){
				//验证的时候，很多数字是不需要验证的，4,6,8,9,10,12,14
				//验证的时候只验证质数
				//目前没有质数，最好计算的质数保存起来
				if( nums[j] < Math.sqrt(i)){
					if(i % nums[j] == 0){
						continue itcast;
					}
				}
			}
			
			//将计算的数据放入数组nums中
			//先检测num的值是否超过了nums的索引范围
			//如果不超过
			if(num == nums.length){
				//如果超过
				//必须将原始数组的空间加大
				int[] temp = new int[nums.length*2];
				//将原始数组的数据拷贝过来
				for(int j = 0;j<nums.length;j++){
					temp[j] = nums[j];
				}
				//保存数据的数组指向新数组
				nums = temp;
			}
			nums[num] = i;
			num++;
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start)+"ms");
		*/
		/*
		for(int i = 0;i<num;i++){
			System.out.print(nums[i]+"\t");
		}
		*/

        //使用数组保存质数16511ms
        //使用原始方式839ms
        //算法的设计不要按照自己大脑中的想法来进行估算

        //总结：
        //1.预先设计的算法在实际执行时未必像想想的那么速度快
        //一定要进行实际的测试
        //2.在算法设计时，不要忽略了一些简单的行为++ [] ==
        //这些操作需要开销时间的

        int a = 0;
        int[] arr = new int[4];
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 1000000000; i++) {
            a = a + 2;
        }

        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");


        //问题：计算100！计算结果末尾有多少个0
//        运算结果末尾的0，全部都是由5和2相乘或者由10得到的
        //问题：计算1000！计算结果末尾有多少个0
//        运算结果末尾的0，全部都是由5和2相乘或者由10得到的

        //问题：计算100！计算结果中有多少个0
        //问题：计算1000！计算结果中有多少个0
        //100的阶乘是无法使用int,long描述的
        //double描述是可以的，但是无法获取结果最后的0的个数

        //问题：计算1234！计算结果中有多少个0
//        Java提供的大整数类型也保存不了1234！
//        构造一种模型
//        String s = "123456";
//        1234567890
//        "12" "3456" "7890"
//        1234567890000
//        "1" "2345"  "6789" "0000"
//        "0"
//        String[]
    }
}