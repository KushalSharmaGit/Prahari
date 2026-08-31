#include <Wire.h>
#include <MPU6050.h>
#include <math.h>

MPU6050 mpu;

// Pin definitions
#define BUZZER_PIN 18
#define MOTOR_PIN 19

// Sharp turn sensitivity
// Increase = less sensitive
// Decrease = more sensitive
#define SHARP_TURN_THRESHOLD 150.0

// How long buzzer and motor stay ON (milliseconds)
#define ALERT_DURATION 500

// Calibration offsets
const int16_t AX_OFFSET = -587;
const int16_t AY_OFFSET = -71;
const int16_t AZ_OFFSET = -1401;

const int16_t GX_OFFSET = -212;
const int16_t GY_OFFSET = 102;
const int16_t GZ_OFFSET = -46;

// Filter angles
float roll = 0;
float pitch = 0;

unsigned long previousTime;
unsigned long alertStartTime = 0;
bool alertActive = false;

void setup() {
Serial.begin(115200);

// Set output pins
pinMode(BUZZER_PIN, OUTPUT);
pinMode(MOTOR_PIN, OUTPUT);

// Start with both OFF
digitalWrite(BUZZER_PIN, LOW);
digitalWrite(MOTOR_PIN, LOW);

// MPU6050 I2C
Wire.begin(21, 22);

mpu.initialize();

if (!mpu.testConnection()) {
Serial.println("MPU6050 connection FAILED!");
while (1);
}

Serial.println("MPU6050 connected!");

previousTime = micros();

delay(1000);
}

void loop() {
int16_t ax, ay, az;
int16_t gx, gy, gz;

mpu.getMotion6(&ax, &ay, &az, &gx, &gy, &gz);

// Apply calibration
ax -= AX_OFFSET;
ay -= AY_OFFSET;
az -= AZ_OFFSET;

gx -= GX_OFFSET;
gy -= GY_OFFSET;
gz -= GZ_OFFSET;

// Convert accelerometer to g
float ax_g = ax / 16384.0;
float ay_g = ay / 16384.0;
float az_g = az / 16384.0;

// Convert gyro to degrees/second
float gx_dps = gx / 131.0;
float gy_dps = gy / 131.0;
float gz_dps = gz / 131.0;

// Calculate time
unsigned long currentTime = micros();
float dt = (currentTime - previousTime) / 1000000.0;
previousTime = currentTime;

// Accelerometer angles
float accelRoll = atan2(ay_g, az_g) * 180.0 / PI;

float accelPitch =
atan2(-ax_g, sqrt(ay_g * ay_g + az_g * az_g))
* 180.0 / PI;

// Gyroscope integration
roll += gx_dps * dt;
pitch += gy_dps * dt;

// Complementary filter
float alpha = 0.98;

roll = alpha * roll + (1.0 - alpha) * accelRoll;
pitch = alpha * pitch + (1.0 - alpha) * accelPitch;

// ==========================================
// SHARP TURN DETECTION
// ==========================================

// Check the strongest rotation axis
float rotationSpeed = max(
fabs(gx_dps),
max(fabs(gy_dps), fabs(gz_dps))
);

// Detect sharp turn
if (rotationSpeed > SHARP_TURN_THRESHOLD && !alertActive) {
Serial.println("!!! SHARP TURN DETECTED !!!");

// Turn ON buzzer and motor
digitalWrite(BUZZER_PIN, HIGH);
digitalWrite(MOTOR_PIN, HIGH);

alertStartTime = millis();
alertActive = true;


}

// Turn OFF alert after ALERT_DURATION
if (alertActive && millis() - alertStartTime >= ALERT_DURATION) {
digitalWrite(BUZZER_PIN, LOW);
digitalWrite(MOTOR_PIN, LOW);

alertActive = false;


}

// Print data
Serial.println("--------------------------------");

Serial.print("Roll: ");
Serial.print(roll, 2);
Serial.println(" degrees");

Serial.print("Pitch: ");
Serial.print(pitch, 2);
Serial.println(" degrees");

Serial.print("Rotation speed: ");
Serial.print(rotationSpeed, 2);
Serial.println(" deg/s");

delay(20);
}
