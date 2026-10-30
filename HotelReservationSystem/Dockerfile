
FROM eclipse-temurin:25-jdk

RUN apt-get update && apt-get install -y --no-install-recommends \
    xvfb x11vnc fluxbox \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY app.jar app.jar

EXPOSE 5900

CMD ["sh", "-c", "export DISPLAY=:0; Xvfb :0 -screen 0 1280x800x24 -ac & sleep 2; fluxbox & x11vnc -display :0 -forever -nopw -listen 0.0.0.0 -shared & exec java -cp app.jar HotelReservationGUI"]
