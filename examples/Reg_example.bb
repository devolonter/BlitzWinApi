Include "BlitzWinApi.bb"
bSetPointer (32514)
bRegSetValue (HKEY_CURRENT_USER,"Software\BlitzWinApi","FirstWord","")
bRegSetValue (HKEY_CURRENT_USER,"Software\BlitzWinApi","SecondWord",Input("Second word? :"))
Print "You say - "+ bRegGetValue (HKEY_CURRENT_USER,"Software\BlitzWinApi","FirstWord")+" "+bRegGetValue (HKEY_CURRENT_USER,"Software\BlitzWinApi","SecondWord")
WaitKey ()