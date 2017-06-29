package kz.allpay.api.model.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mark jay
 */
public class BusinessClientPublicApiResponse extends AbstractResponse implements Serializable {
    private String companyName;
    private String category;
    private String fieldOfActivity;
    private String city;
    private String description;
    private String share;
    private List<BusinessClientWorkingDays> businessClientWorkingDayses;

    public static class BusinessClientWorkingDays implements Serializable {
        private String address;
        private List<DayWorkingHours> dayWorkingHours;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<DayWorkingHours> getDayWorkingHours() {
            return dayWorkingHours;
        }

        public void setDayWorkingHours(List<DayWorkingHours> dayWorkingHours) {
            this.dayWorkingHours = dayWorkingHours;
        }
    }

    public static class DayWorkingHours implements Serializable {
        private String day;
        private String fromTime;
        private String toTime;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getFromTime() {
            return fromTime;
        }

        public void setFromTime(String fromTime) {
            this.fromTime = fromTime;
        }

        public String getToTime() {
            return toTime;
        }

        public void setToTime(String toTime) {
            this.toTime = toTime;
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(String fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public List<BusinessClientWorkingDays> getBusinessClientWorkingDayses() {
        return businessClientWorkingDayses;
    }

    public void setBusinessClientWorkingDayses(List<BusinessClientWorkingDays> businessClientWorkingDayses) {
        this.businessClientWorkingDayses = businessClientWorkingDayses;
    }
}
