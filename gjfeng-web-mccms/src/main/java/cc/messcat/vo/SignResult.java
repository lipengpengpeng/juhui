package cc.messcat.vo;

public class SignResult extends CommonResult {
	
	public SignResult(String state, String msg) {
		super(state, msg);
	}

	private String goldenPoints;//黑珍珠
	private String whitePoints;//白珍珠
	private String silverPoints;//贝壳数

	public String getGoldenPoints() {
		return goldenPoints;
	}

	public void setGoldenPoints(String goldenPoints) {
		this.goldenPoints = goldenPoints;
	}

	public String getWhitePoints() {
		return whitePoints;
	}

	public void setWhitePoints(String whitePoints) {
		this.whitePoints = whitePoints;
	}

	public String getSilverPoints() {
		return silverPoints;
	}

	public void setSilverPoints(String silverPoints) {
		this.silverPoints = silverPoints;
	}

}
