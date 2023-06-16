## Dental Office Web Application

Project made using Eclipses Dynamic Web App, with the help of JSP pages.

### Description
Dental office with 9-5 working hours. Web application that can be both used 
by patients and by a dentist. Office has a single dentist. Patients can make 
and cancel their appointments. Dentist can also make and cancel appointments, 
difference being that they can do it for every single appointment. Appointments 
can be either 30 or 60 minutes long and time is flexible. For example, we can 
have an appointment from 09:00 to 09:30, and then another appointment from 
09:30 to 10:30 and etc. Appointments can only be canceled if they haven't passed
deadline, which is a value that is stored and can be changed by the dentist. 
There's also functionality where both patient and dentist get an e-mail every
time appointment is either made or canceled.

### Demo
[Youtube video](https://youtu.be/Z-rcugL_DSo)

### Some notes
1. I HIGHLY reccomend you turn off e-mail sending for testing (disable EmailUtility class), 
because it's so slow and Google probably doesn't like that much spamming
2. DentalOfficeSQLScript.sql can be used to generate tables required. Dentist HAS to be 
manually added, but patients and appointments can be added through application
3. File Napomene.txt contains some notes and decisions (in Serbian) that I made during development
