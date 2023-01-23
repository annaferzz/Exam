package ferzz.convertion;

public class ConvertPlane {
        //region Размеры декартовой плоскости
        private int realWidth;
        private int realHeight;
        //endregion

        //region Аргументы декартовой плоскости
        private double xMin;
        private double xMax;
        private double yMin;
        private double yMax;
        //endregion

        /**
         * @param realWidth ширина экрана
         * @param realHeight высота экрана
         * */
        public ConvertPlane(int realWidth, int realHeight,
                            double xMin, double xMax, double yMin, double yMax) {
            this.realWidth = realWidth;
            this.realHeight = realHeight;
            this.xMin = xMin;
            this.xMax = xMax;
            this.yMin = yMin;
            this.yMax = yMax;
        }

        //region Сеттеры всех полей
        public void setXMin(double xMin){
            this.xMin = xMin;
        }
        public void setXMax(double xMax){
            this.xMax = xMax;
        }
        public void setYMin(double yMin){
            this.yMin = yMin;
        }
        public void setYMax(double yMax){
            this.yMax = yMax;
        }
        public void setRealWidth(int RealWidth){
            this.realWidth = Math.abs(RealWidth);
        }
        public void setRealHeight(int RealHeight){
            this.realHeight = Math.abs(RealHeight);
        }
        //endregion

        //region Геттеры всех полей
        public int getWidth() { return realWidth - 1; }
        public int getHeight() {
            return realHeight - 1;
        }
        public double getXMin() {
            return xMin;
        }
        public double getXMax() {
            return xMax;
        }
        public double getYMin() {
            return yMin;
        }
        public double getYMax() {
            return yMax;
        }
        //endregion

        /**
         * Отношение ширины экрана к длине обозримой оси X в текущем контексте декартовой плоскости
         * */
        public double getXDen() {
            return getWidth() / (xMax - xMin);
        }

        /**
         * Отношение высоты экрана к длине обозримой оси Y в текущем контексте декартовой плоскости
         * */
        public double getYDen() {
            return getHeight() / (yMax - yMin);
        }
}
