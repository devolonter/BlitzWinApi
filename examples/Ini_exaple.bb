Include "BlitzWinApi.bb"
BWA_INI_FILENAME = CurrentDir()+"my_first.ini"
bIniSetValue ("say","first",Input("First word? :"))
bIniSetValue ("say","second",Input("Second word? :"))
Print "You say - "+bIniGetValue ("say","First")+" "+bIniGetValue ("say","second")+" "+bIniGetValue ("say","thrid","Oooops ;)")
WaitKey ()
  