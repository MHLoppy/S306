// Based on circuit design and code by Learn It Easily
//   who uploaded once and never again, RIP
// https://youtu.be/BLpzOAYacW0

// Set pin values
const uint8_t LDR_PIN = A0;
const uint8_t LED_PIN = 7;

// Threshold for LDR to trigger
const int LIGHT_THRESHOLD = 500;

// Track darkness (inverse of light)
bool is_dark = false;

void setup()
{
  pinMode(LED_PIN, OUTPUT);
  
  Serial.begin(9600);
}

void loop()
{
  // SENSE light level
  int dark_level = analogRead(A0);
  
  // THINK about war, peace, and light level
  if(LIGHT_THRESHOLD > dark_level)
  {
    is_dark = false;
  }
  else
  {
    is_dark = true;
  }
  
  // ACT depending on light level
  digitalWrite(LED_PIN, is_dark);
  
  // LOG old growth forests to induce climate change
  Serial.println(dark_level);
  Serial.println(is_dark);
  
  // Slow the loop down to make it easier to follow along
  delay(500);
}
