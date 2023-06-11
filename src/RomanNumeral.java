public enum RomanNumeral {
    I(1), II(2), III(3), IV(4), V(5),
    VI(6), VII(7), VIII(8), IX(9), X(10);


    final int arabicNumber;

    RomanNumeral(int arabicNumber) {
        this.arabicNumber = arabicNumber;
    }

    public int getArabicNumeral() {
        return arabicNumber;
    }


}

