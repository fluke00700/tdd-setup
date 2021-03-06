package tdd.setup;

// behaviour inspired by https://www.online-calculator.com/
public class Calculator {

        private String screen = "0";

        private double latestValue;

        private boolean equalPressed = false;

        private String latestOperation = "";

        public String readScreen() {
                if(screen.startsWith("0"))
                {
                        screen = screen.substring(1);
                }
                return screen;
        }
        public void pressDigitKey(int digit) {
                if(digit > 9 || digit < 0) throw new IllegalArgumentException();

                if(latestOperation.isEmpty()) {
                        screen = screen + String.valueOf(digit);
                        latestValue = Double.parseDouble(screen);
                } else {

                        screen = screen + String.valueOf(digit);

                }
        }

        public void pressClearKey() {
                latestOperation = "";
                latestValue = 0.0;
        }

        public void pressOperationKey(String operation)  {
                latestOperation = operation;
                screen = "0";
                ;
        }

        public void pressDotKey() {
                if(!screen.endsWith(".")) screen = screen + ".";
        }

        public void pressNegative() {
                if(screen.startsWith("0"))
                {
                        screen = screen.substring(1);
                        screen = "-" + screen;
                }
                else {
                        screen = "-" + screen;
                }
        }


        public void pressEquals() {

                var result = switch(latestOperation) {
                        case "+" -> latestValue + Double.parseDouble(screen);
                        case "-" -> latestValue - Double.parseDouble(screen);
                        case "x" -> latestValue * Double.parseDouble(screen);
                        case "/" -> latestValue / Double.parseDouble(screen);
                        default -> throw new IllegalArgumentException();
                };
                screen = Double.toString(result);
                if(screen.endsWith(".0")) screen = screen.substring(0,screen.length()-2);
        }
}