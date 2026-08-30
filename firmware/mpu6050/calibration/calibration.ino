#include <Wire.h>
#include <MPU6050.h>

MPU6050 mpu;

long axOffset = 0;
long ayOffset = 0;
long azOffset = 0;

long gxOffset = 0;
long gyOffset = 0;
long gzOffset = 0;

void setup() {
  Serial.begin(115200);

  Wire.begin(21, 22);

  mpu.initialize();

  if (!mpu.testConnection()) {
    Serial.println("MPU6050 connection FAILED!");
    while (1);
  }

  Serial.println("MPU6050 connected!");
  Serial.println();
  Serial.println("CALIBRATION STARTING...");
  Serial.println("Keep the MPU6050 COMPLETELY STILL!");
  delay(3000);

  long axSum = 0;
  long aySum = 0;
  long azSum = 0;

  long gxSum = 0;
  long gySum = 0;
  long gzSum = 0;

  const int samples = 2000;

  for (int i = 0; i < samples; i++) {

    int16_t ax, ay, az;
    int16_t gx, gy, gz;

    mpu.getMotion6(&ax, &ay, &az, &gx, &gy, &gz);

    axSum += ax;
    aySum += ay;
    azSum += az;

    gxSum += gx;
    gySum += gy;
    gzSum += gz;

    delay(2);
  }

  axOffset = axSum / samples;
  ayOffset = aySum / samples;

  // Z should measure approximately +1g when resting flat
  azOffset = (azSum / samples) - 16384;

  gxOffset = gxSum / samples;
  gyOffset = gySum / samples;
  gzOffset = gzSum / samples;

  Serial.println();
  Serial.println("===== CALIBRATION RESULTS =====");

  Serial.print("AX offset: ");
  Serial.println(axOffset);

  Serial.print("AY offset: ");
  Serial.println(ayOffset);

  Serial.print("AZ offset: ");
  Serial.println(azOffset);

  Serial.print("GX offset: ");
  Serial.println(gxOffset);

  Serial.print("GY offset: ");
  Serial.println(gyOffset);

  Serial.print("GZ offset: ");
  Serial.println(gzOffset);

  Serial.println("===============================");
}

void loop() {
}