/**
 * Class represents Capital entity
 */
public class Capital {

    Integer capitalId;
    String country;
    String capital;
    Integer userId;

    public Capital(Integer capitalId, String country, String capital, Integer userId) {
        this.capitalId = capitalId;
        this.country = country;
        this.capital = capital;
        this.userId = userId;
    }

    public Capital() {
    }

    public Integer getCapitalId() {
        return capitalId;
    }

    public void setCapitalId(Integer capitalId) {
        this.capitalId = capitalId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
