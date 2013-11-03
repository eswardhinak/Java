public class ReadArgs
{
	public static void main(String args[])
	{
		int numargs = args.length;
		int argindex = 0;
		int argvalue;
		int arg1 = 1, arg2 = 1, arg3 = 1;
		if (args.length >= 3)
		{
			arg1 = Integer.parseInt(args[0]);
			arg2 =  Integer.parseInt(args[1]);
			arg3 = Integer.parseInt(args[2]);
		}
		if (args.length == 2)
		{
			arg1 = Integer.parseInt(args[0]);
			arg2 = Integer.parseInt(args[1]);
		}
		if (args.length == 1)
		{
			arg1 = Integer.parseInt(args[0]);
		}
		if (args.length>0)
		{
		System.out.println("Product is " + arg1*arg2*arg3);
		}
	}
}
		