package com.generated;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "employee"
})
@XmlRootElement(name = "staff")
public class Staff {

    protected List<Employee> employee;

    public List<Employee> getEmployee() {
        if (employee == null) {
            employee = new ArrayList<Employee>();
        }
        return this.employee;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "name",
            "salary",
            "hiredate"
    })
    public static class Employee {

        @XmlElement(required = true)
        protected String name;
        protected int salary;
        @XmlElement(required = true)
        protected Hiredate hiredate;

        /**
         * Gets the value of the name property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the salary property.
         */
        public int getSalary() {
            return salary;
        }

        /**
         * Sets the value of the salary property.
         */
        public void setSalary(int value) {
            this.salary = value;
        }

        /**
         * Gets the value of the hiredate property.
         *
         * @return possible object is
         * {@link com.generated.Staff.Employee.Hiredate }
         */
        public Hiredate getHiredate() {
            return hiredate;
        }

        /*@Override
        public String toString() {
            return "Employee { " +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    ", hiredate=" + hiredate +
                    " }";
        }*/

        /**
         * Sets the value of the hiredate property.
         *
         * @param value allowed object is
         *              {@link com.generated.Staff.Employee.Hiredate }
         */
        public void setHiredate(Hiredate value) {
            this.hiredate = value;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "value"
        })
        public static class Hiredate {

            @XmlValue
            protected String value;

            @XmlAttribute(name = "year", required = true)
            protected short year;

            @XmlAttribute(name = "month", required = true)
            protected byte month;

            @XmlAttribute(name = "day", required = true)
            protected byte day;

            /**
             * Gets the value of the value property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the year property.
             */
            public short getYear() {
                return year;
            }

            /**
             * Sets the value of the year property.
             */
            public void setYear(short value) {
                this.year = value;
            }

            /**
             * Gets the value of the month property.
             */
            public byte getMonth() {
                return month;
            }

            /**
             * Sets the value of the month property.
             */
            public void setMonth(byte value) {
                this.month = value;
            }

            /**
             * Gets the value of the day property.
             */
            public byte getDay() {
                return day;
            }

            /**
             * Sets the value of the day property.
             */
            public void setDay(byte value) {
                this.day = value;
            }

            /*@Override
            public String toString() {
                return year +
                        "-" + month +
                        "-" + day +
                        " }";
            }*/
        }

    }

}
