package th.or.nectec.thai.address;

public class InvalidAddressCodeFormatException extends RuntimeException {

    public InvalidAddressCodeFormatException(String message, String code) {
        super(message + " [" + code + "]");
    }

    public static class InvalidProvinceCodeException extends InvalidAddressCodeFormatException {

        public InvalidProvinceCodeException(String code) {
            super("Invalid province code format", code);
        }
    }

    public static class InvalidDistrictCodeException extends InvalidAddressCodeFormatException {

        public InvalidDistrictCodeException(String code) {
            super("Invalid district code format", code);
        }
    }

    public static class InvalidSubDistrictCodeException extends InvalidAddressCodeFormatException {

        public InvalidSubDistrictCodeException(String code) {
            super("Invalid sub-district code format", code);
        }
    }
}
