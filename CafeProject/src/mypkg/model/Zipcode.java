package mypkg.model;

//Bean(��) : 1�ǿ� ���� ������ ǥ���ϱ� ���� Ŭ����
public class Zipcode {
	private String zipcode ; //�����ȣ�� ������ ����
	private String sido ; //���� �Ǵ� ���� ������ ����
	private String gugun ; //�� �Ǵ� �ҵ��ø� ������ ����
	private String dong ; //�� �Ǵ� ��, ���� ������ ����
	private String bunji ; //�ּ��� ������ ������ ����
	private int seqnum ;
	
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getBunji() {
		return bunji;
	}
	public void setBunji(String bunji) {
		this.bunji = bunji;
	}
	public int getSeqnum() {
		return seqnum;
	}
	public void setSeqnum(int seqnum) {
		this.seqnum = seqnum;
	}
	
}