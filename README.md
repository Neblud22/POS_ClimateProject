# POS_ClimateProject
Angi&amp;Luis POS/SYP Climate Project: Rain Data

# Rain Data Analysis
+  Data sources 
    + NASA POWER API (daily point data)
    + PRECTOTCORR = precipitation (rain/snow)
    + T2M = temperature at 2 meters 
    + RH2M = relative humidity at 2 meters
+ Main features
    + Get climate data by location (latitude / longitude)
    + Get data by date
    + Access single parameters or combined data
    + Store external data locally for faster access
    + Serve data through REST API
+ Flow
  + Request data from NASA API
  + Convert response to JSON
  + Save JSON locally
  + Insert data into database
  + Access data via endpoints