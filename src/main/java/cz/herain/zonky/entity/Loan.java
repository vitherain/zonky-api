package cz.herain.zonky.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by Vít on 16. 10. 2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Loan implements Serializable {

    private static final long serialVersionUID = 1234984946498496L;

    private Long id;
    private String name;
    private String story;
    private String purpose;
    private List<Photo> photos;
    private String nickName;
    private int termInMonths;
    private float interestRate;
    private String rating;
    private String topped;
    private float amount;
    private float remainingInvestment;
    private float investmentRate;
    private boolean covered;
    private OffsetDateTime datePublished;
    private boolean published;
    private OffsetDateTime deadline;
    private int investmentsCount;
    private int questionsCount;
    private Long region;
    //it could be enum if if I knew all possible values
    private String mainIncomeType;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isCovered() {
        return covered;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }

    public OffsetDateTime getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(OffsetDateTime datePublished) {
        this.datePublished = datePublished;
    }

    public OffsetDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(OffsetDateTime deadline) {
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public float getInvestmentRate() {
        return investmentRate;
    }

    public void setInvestmentRate(float investmentRate) {
        this.investmentRate = investmentRate;
    }

    public int getInvestmentsCount() {
        return investmentsCount;
    }

    public void setInvestmentsCount(int investmentsCount) {
        this.investmentsCount = investmentsCount;
    }

    public String getMainIncomeType() {
        return mainIncomeType;
    }

    public void setMainIncomeType(String mainIncomeType) {
        this.mainIncomeType = mainIncomeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Long getRegion() {
        return region;
    }

    public void setRegion(Long region) {
        this.region = region;
    }

    public float getRemainingInvestment() {
        return remainingInvestment;
    }

    public void setRemainingInvestment(float remainingInvestment) {
        this.remainingInvestment = remainingInvestment;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(int termInMonths) {
        this.termInMonths = termInMonths;
    }

    public String getTopped() {
        return topped;
    }

    public void setTopped(String topped) {
        this.topped = topped;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        if (id != null ? !id.equals(loan.id) : loan.id != null) return false;
        if (name != null ? !name.equals(loan.name) : loan.name != null) return false;
        if (nickName != null ? !nickName.equals(loan.nickName) : loan.nickName != null) return false;
        return datePublished != null ? datePublished.equals(loan.datePublished) : loan.datePublished == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (datePublished != null ? datePublished.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                ", story='" + story + '\'' +
                ", purpose='" + purpose + '\'' +
                ", photos=" + photos +
                ", nickName='" + nickName + '\'' +
                ", termInMonths=" + termInMonths +
                ", interestRate=" + interestRate +
                ", rating='" + rating + '\'' +
                ", topped='" + topped + '\'' +
                ", remainingInvestment=" + remainingInvestment +
                ", investmentRate=" + investmentRate +
                ", covered=" + covered +
                ", datePublished=" + datePublished +
                ", published=" + published +
                ", deadline=" + deadline +
                ", investmentsCount=" + investmentsCount +
                ", questionsCount=" + questionsCount +
                ", region=" + region +
                ", mainIncomeType=" + mainIncomeType +
                '}';
    }

    public static class LoanBuilder {
        private float amount;
        private Long id;
        private String name;
        private String story;
        private String purpose;
        private List<Photo> photos;
        private String nickName;
        private int termInMonths;
        private float interestRate;
        private String rating;
        private String topped;
        private float remainingInvestment;
        private float investmentRate;
        private boolean covered;
        private OffsetDateTime datePublished;
        private boolean published;
        private OffsetDateTime deadline;
        private int investmentsCount;
        private int questionsCount;
        private Long region;
        private String mainIncomeType;

        private LoanBuilder() {
        }

        public static LoanBuilder aLoan() {
            return new LoanBuilder();
        }

        public LoanBuilder withAmount(float amount) {
            this.amount = amount;
            return this;
        }

        public LoanBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public LoanBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public LoanBuilder withStory(String story) {
            this.story = story;
            return this;
        }

        public LoanBuilder withPurpose(String purpose) {
            this.purpose = purpose;
            return this;
        }

        public LoanBuilder withPhotos(List<Photo> photos) {
            this.photos = photos;
            return this;
        }

        public LoanBuilder withNickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public LoanBuilder withTermInMonths(int termInMonths) {
            this.termInMonths = termInMonths;
            return this;
        }

        public LoanBuilder withInterestRate(float interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        public LoanBuilder withRating(String rating) {
            this.rating = rating;
            return this;
        }

        public LoanBuilder withTopped(String topped) {
            this.topped = topped;
            return this;
        }

        public LoanBuilder withRemainingInvestment(float remainingInvestment) {
            this.remainingInvestment = remainingInvestment;
            return this;
        }

        public LoanBuilder withInvestmentRate(float investmentRate) {
            this.investmentRate = investmentRate;
            return this;
        }

        public LoanBuilder withCovered(boolean covered) {
            this.covered = covered;
            return this;
        }

        public LoanBuilder withDatePublished(OffsetDateTime datePublished) {
            this.datePublished = datePublished;
            return this;
        }

        public LoanBuilder withPublished(boolean published) {
            this.published = published;
            return this;
        }

        public LoanBuilder withDeadline(OffsetDateTime deadline) {
            this.deadline = deadline;
            return this;
        }

        public LoanBuilder withInvestmentsCount(int investmentsCount) {
            this.investmentsCount = investmentsCount;
            return this;
        }

        public LoanBuilder withQuestionsCount(int questionsCount) {
            this.questionsCount = questionsCount;
            return this;
        }

        public LoanBuilder withRegion(Long region) {
            this.region = region;
            return this;
        }

        public LoanBuilder withMainIncomeType(String mainIncomeType) {
            this.mainIncomeType = mainIncomeType;
            return this;
        }

        public LoanBuilder but() {
            return aLoan().withAmount(amount).withId(id).withName(name).withStory(story).withPurpose(purpose).withPhotos(photos).withNickName(nickName).withTermInMonths(termInMonths).withInterestRate(interestRate).withRating(rating).withTopped(topped).withRemainingInvestment(remainingInvestment).withInvestmentRate(investmentRate).withCovered(covered).withDatePublished(datePublished).withPublished(published).withDeadline(deadline).withInvestmentsCount(investmentsCount).withQuestionsCount(questionsCount).withRegion(region).withMainIncomeType(mainIncomeType);
        }

        public Loan build() {
            Loan loan = new Loan();
            loan.setAmount(amount);
            loan.setId(id);
            loan.setName(name);
            loan.setStory(story);
            loan.setPurpose(purpose);
            loan.setPhotos(photos);
            loan.setNickName(nickName);
            loan.setTermInMonths(termInMonths);
            loan.setInterestRate(interestRate);
            loan.setRating(rating);
            loan.setTopped(topped);
            loan.setRemainingInvestment(remainingInvestment);
            loan.setInvestmentRate(investmentRate);
            loan.setCovered(covered);
            loan.setDatePublished(datePublished);
            loan.setPublished(published);
            loan.setDeadline(deadline);
            loan.setInvestmentsCount(investmentsCount);
            loan.setQuestionsCount(questionsCount);
            loan.setRegion(region);
            loan.setMainIncomeType(mainIncomeType);
            return loan;
        }
    }
}
