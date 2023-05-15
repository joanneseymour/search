package search;

public class BusRoutesGraph{
	
	GNode home = new GNode("home");
	static GNode nan = new GNode("南村");
	static GNode gong = new GNode("公交新村");
	static GNode xiang = new GNode("香宁花园");
	static GNode zhu = new GNode("竹苑");
	static GNode jiu = new GNode("九洲城");
	static GNode gao = new GNode("高级技校");
	static GNode work = new GNode("work");
	
	Edge homeNan = new Edge("homeNan", home, nan, 5);
	Edge homeGong = new Edge("homeGong", home, gong, 4);
	Edge homeXiang = new Edge("homeXiang", home, xiang, 15);
	Edge nanGong = new Edge("nanGong", nan, gong, 2);
	Edge gongXiang = new Edge("gongXiang", gong, xiang, 5);
	Edge nanXiang = new Edge("nanXiang", nan, xiang, 7);
	Edge nanZhu = new Edge("nanZhu", nan, zhu, 6);
	Edge nanJiu = new Edge("nanJiu", nan, jiu, 12);
	Edge xiangJiu = new Edge("xiangJiu", xiang, jiu, 12);
	Edge xiangGao = new Edge("xiangGao", xiang, gao, 9);
	Edge zhuGao = new Edge("zhuGao", zhu, gao, 9);
	Edge jiuGao = new Edge("jiuGao", jiu, gao, 2);
	Edge jiuWork = new Edge("jiuWork", jiu, work, 12);
	Edge gaoWork = new Edge("gaoWork", gao, work, 1);

	

	
	public BusRoutesGraph() {
		home.children.add(nan);
		home.edges.add(homeNan);
		home.children.add(gong);
		home.edges.add(homeGong);
		home.children.add(xiang);
		home.edges.add(homeXiang);
		
		nan.children.add(gong);
		nan.edges.add(nanGong);
		nan.children.add(zhu);
		nan.edges.add(nanZhu);
		nan.children.add(jiu);
		nan.edges.add(nanJiu);
		nan.children.add(xiang);
		nan.edges.add(nanXiang);
		nan.parents.add(home);
		
		gong.children.add(xiang);
		gong.edges.add(gongXiang);
		gong.parents.add(home);
		gong.parents.add(nan);

		xiang.children.add(jiu);
		xiang.edges.add(xiangJiu);
		xiang.children.add(gao);
		xiang.edges.add(xiangGao);
		xiang.parents.add(home);
		xiang.parents.add(gong);
		
		zhu.children.add(gao);
		zhu.edges.add(zhuGao);
		zhu.parents.add(nan);
		
		jiu.children.add(gao);
		jiu.edges.add(jiuGao);
		jiu.children.add(work);
		jiu.edges.add(jiuWork);
		jiu.parents.add(xiang);
		jiu.parents.add(nan);
		
		gao.children.add(work);
		gao.edges.add(gaoWork);
		gao.parents.add(jiu);
		gao.parents.add(zhu);
		gao.parents.add(xiang);
		
		work.parents.add(gao);	
		work.parents.add(jiu);
	}

}
