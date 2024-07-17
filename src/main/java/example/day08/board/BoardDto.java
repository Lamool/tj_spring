package example.day08.board;

public class BoardDto {
    // 매개변수
    private int bno;
    private String bdate;
    private String btitle;
    private int bview;
    private String bcontent;
    private String bpassword;

    // getter/setter
    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public int getBview() {
        return bview;
    }

    public void setBview(int bview) {
        this.bview = bview;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public String getBpassword() {
        return bpassword;
    }

    public void setBpassword(String bpassword) {
        this.bpassword = bpassword;
    }
}
