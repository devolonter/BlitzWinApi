;константы
Const BWA_DLL_NAME$ = "BlitzWinApi"

Const HKEY_CLASSES_ROOT = $80000000
Const HKEY_CURRENT_USER = $80000001
Const HKEY_LOCAL_MACHINE = $80000002
Const HKEY_USERS = $80000003
Const HKEY_PERFORMANCE_DATA = $80000004
Const HKEY_CURRENT_CONFIG = $80000005
Const HKEY_DYN_DATA = $80000006

Const MB_ABORTRETRYIGNORE = 2
Const MB_OK = 0
Const MB_OKCANCEL = 1
Const MB_RETRYCANCEL = 5
Const MB_YESNO = 4
Const MB_YESNOCANCEL = 3

Const IDOK = 1
Const IDCANCEL = 2
Const IDABORT = 3
Const IDRETRY = 4
Const IDIGNORE = 5
Const IDYES = 6
Const IDNO = 7

Const MF_SEPARATOR = $800
Const MF_ENABLED = 0
Const MF_DISABLED = $3
Const MF_UNCHECKED = 0
Const MF_CHECKED = $8
Const MF_POPUP = $10

Const POINTER_APPSTARTING = 32650 
Const POINTER_ARROW = 32512
Const POINTER_CROSS = 32515
Const POINTER_IBEAM = 32513 
Const POINTER_NO = 32648
Const POINTER_SIZEALL = 32646  
Const POINTER_SIZENESW = 32643 
Const POINTER_SIZENS = 32645 
Const POINTER_SIZENWSE = 32642 
Const POINTER_SIZEWE = 32644 
Const POINTER_UPARROW = 32516 
Const POINTER_WAIT = 32514

Const SW_HIDE = 0
Const SW_SHOWMAXIMIZED = 3
Const SW_SHOWMINIMIZED = 2
Const SW_SHOWMINNOACTIVE = 7
Const SW_SHOWNOACTIVATE = 4
Const SW_SHOWNORMAL = 1

Const WS_BORDER = $800000
Const WS_CAPTION = $C00000
Const WS_CHILD = $40000000
Const WS_CHILDWINDOW = $40000000
Const WS_CLIPCHILDREN = $2000000
Const WS_CLIPSIBLINGS = $4000000
Const WS_DISABLED = $8000000
Const WS_DLGFRAME = $400000
Const WS_GROUP = $20000
Const WS_HSCROLL = $100000
Const WS_ICONIC = $20000000
Const WS_MAXIMIZE = $1000000
Const WS_MAXIMIZEBOX = $10000
Const WS_MINIMIZE = $20000000
Const WS_MINIMIZEBOX = $20000
Const WS_OVERLAPPED = $0
Const WS_OVERLAPPEDWINDOW = $CF0000
Const WS_POPUP = $80000000
Const WS_POPUPWINDOW = $80880000
Const WS_SIZEBOX = $40000
Const WS_SYSMENU = $80000
Const WS_TABSTOP = $10000
Const WS_THICKFRAME = $40000
Const WS_TILED = $0
Const WS_TILEDWINDOW = $CF0000
Const WS_VISIBLE = $10000000
Const WS_VSCROLL = $200000

Const WS_EX_ACCEPTFILES = $10
Const WS_EX_APPWINDOW = $40000
Const WS_EX_CLIENTEDGE = $200
Const WS_EX_CONTEXTHELP = $400
Const WS_EX_CONTROLPARENT = $10000
Const WS_EX_DLGMODALFRAME = $1
Const WS_EX_LEFT = $0
Const WS_EX_LEFTSCROLLBAR = $4000
Const WS_EX_LTRREADING = $0
Const WS_EX_MDICHILD = $40
Const WS_EX_NOPARENTNOTIFY = $4
Const WS_EX_OVERLAPPEDWINDOW = $300
Const WS_EX_PALETTEWINDOW = $188
Const WS_EX_RIGHT = $1000
Const WS_EX_RIGHTSCROLLBAR = $0
Const WS_EX_RTLREADING = $2000
Const WS_EX_STATICEDGE = $20000
Const WS_EX_TOOLWINDOW = $80
Const WS_EX_TOPMOST = $8
Const WS_EX_TRANSPARENT = $20
Const WS_EX_WINDOWEDGE = $100

Const GWL_EXSTYLE  = (-20)
Const GWL_STYLE = (-16) 

Global BWA_INI_FILENAME$ = CurrentDir()+"BlitzWinApi.ini"
Global BWA_CURRENT_HWND = SystemProperty("AppHwnd")
Global BWA_DRAG_START = 0
Global BWA_COLOR_BANK = CreateBank(3)
Global BWA_MESSAGE_BANK = CreateBank(8)

; not api functions

; windows
Function bSetWindowAlpha (alpha, hWnd%=0)
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	bSetWindowLong(-20,$80000,hWnd)
	bSetLayeredWindowAttributes(0, alpha, $2, hWnd)	
End Function

Function bFreeWindowAlpha (hWnd%=0)
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	bSetWindowLong(-20, 0, hWnd)
End Function

Function bWindowIsActive (hWnd%=0)	
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	If bGetActiveWindow () <> hWnd Return 0
	Return 1
End Function

Function bDragAcceptFiles  (hWnd%=0)
	bSetWindowLong(-20,$10,hWnd)	
End Function

Function bDisableExitButton (hWnd%=0)
	Local menu, count, result
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	menu = bGetSystemMenu (0,hWnd)
	count = bGetMenuItemCount (menu)
	result = bRemoveMenu (menu,count-1,$400)
	bDrawMenuBar (hWnd)
	Return result	
End Function

Function bGetWindowX (hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankOut	
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankOut = bGetWindowRect (hWnd)
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,0)
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result	
End Function

Function bGetWindowRight (hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankOut	
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankOut = bGetWindowRect (hWnd)
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,4)
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result	
End Function

Function bGetWindowY (hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankOut	
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankOut = bGetWindowRect (hWnd)
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,8)
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result	
End Function

Function bGetWindowBottom (hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankOut	
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankOut = bGetWindowRect (hWnd)
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,12)
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result	
End Function

Function bGetWindowWidth (hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankOut	
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankOut = bGetWindowRect (hWnd)
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,4) - PeekInt (blitzwinapi_bankOut,0) 
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result	
End Function

Function bGetWindowHeight (hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankOut	
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankOut = bGetWindowRect (hWnd)
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,12) - PeekInt (blitzwinapi_bankOut,8) 
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result	
End Function

Function bScreenWidth ()
	Return bGetWindowWidth(bGetDesktop())
End Function

Function bScreenHeight ()
	Return bGetWindowHeight(bGetDesktop())
End Function

; color bank
Function bRequestRed ()
	Return PeekByte (BWA_COLOR_BANK,0)
End Function

Function bRequestGreen ()
	Return PeekByte (BWA_COLOR_BANK,1)
End Function

Function bRequestBlue ()
	 Return PeekByte (BWA_COLOR_BANK,2)
End Function

; messages
Function bGetEventWParam ()
	Return PeekInt (BWA_MESSAGE_BANK,0)
End Function

Function bGetEventLParam ()
	Return PeekInt (BWA_MESSAGE_BANK,4)
End Function 

;Api functions

; window descriptors
Function bGetActiveWindow ()
	Return CallDLL (BWA_DLL_NAME,"WinApi_GetActiveWindow")
End Function

Function bWindowFromPoint (x%,y%)
	Local blitzwinapi_result, blitzwinapi_bankIn	
	blitzwinapi_bankIn = CreateBank (8)
	PokeInt (blitzwinapi_bankIn,0,x)
	PokeInt (blitzwinapi_bankIn,4,y)	
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_WindowFromPoint",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bFindWindow (caption$)
	Local blitzwinapi_result, blitzwinapi_bankIn	
	blitzwinapi_bankIn = CreateBank (Len(caption)+1)
	PokeString (blitzwinapi_bankIn,caption)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_FindWindow",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bGetDesktop ()
	Return CallDLL (BWA_DLL_NAME,"WinApi_GetDesktop")	
End Function

; windows
Function bSetActiveWindow (hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (4)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_SetActiveWindow",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bGetWindowRect (hWnd%=0)
	Local blitzwinapi_bankIn, blitzwinapi_bankOut
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (4)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	blitzwinapi_bankOut = CreateBank (16)
	CallDLL (BWA_DLL_NAME,"WinApi_GetWindowRect",blitzwinapi_bankIn,blitzwinapi_bankOut)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_bankOut	
End Function

Function bHideWindow (hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankIn	
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (4)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_HideWindow",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bShowWindow (hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankIn	
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (4)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_ShowWindow",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bFlashWindow (time=1000, hwnd=0)
	Local time_flash, flash = 0
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	Repeat				
		flash = Not Flash
		time_flash = MilliSecs ()
		bSetFlashWindow (flash, hWnd%)
		Delay time
	Until (bWindowIsActive (hWnd))
End Function

Function bSetFlashWindow (flash, hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankIn	
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (8)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	PokeInt (blitzwinapi_bankIn,4,flash)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_FlashWindow",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bGetWindowLong (index%,hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (8)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	PokeInt (blitzwinapi_bankIn,4,index)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_GetWindowLong",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bSetWindowLong (index%, newlong%, hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (12)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	PokeInt (blitzwinapi_bankIn,4,index)
	PokeInt (blitzwinapi_bankIn,8,newlong)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_SetWindowLong",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

Function bSetLayeredWindowAttributes (crKey%,bAlpha%,dwFlags%, hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (16)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	PokeInt (blitzwinapi_bankIn,4,crKey)
	PokeInt (blitzwinapi_bankIn,8,bAlpha)
	PokeInt (blitzwinapi_bankIn,12,dwFlags)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_SetLayeredWindowAttributes",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

Function bCreateRectRegion (l%, t%, r%, b%)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (16)
	PokeInt (blitzwinapi_bankIn,0,l)
	PokeInt (blitzwinapi_bankIn,4,t)
	PokeInt (blitzwinapi_bankIn,8,r)
	PokeInt (blitzwinapi_bankIn,12,b)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_CreateRectRegion",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bSetWindowRegion (region%, hwnd% = 0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (8)
	PokeInt (blitzwinapi_bankIn,0,hwnd)
	PokeInt (blitzwinapi_bankIn,4,region)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_SetRectRegion",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

; menu descriptors
Function bGetSystemMenu (revert,hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (8)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	PokeInt (blitzwinapi_bankIn,4,revert)	
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_GetSystemMenu",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

; menu
Function bGetMenuItemCount (hWmnu%)
	Local blitzwinapi_result, blitzwinapi_bankIn
	blitzwinapi_bankIn = CreateBank (4)
	PokeInt (blitzwinapi_bankIn,0,hWmnu)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_GetMenuItemCount",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bDrawMenuBar (hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (4)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_DrawMenuBar",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bRemoveMenu (hWmnu%,position%,flag%)
	Local blitzwinapi_result, blitzwinapi_bankIn
	blitzwinapi_bankIn = CreateBank (12)
	PokeInt (blitzwinapi_bankIn,0,hWmnu)
	PokeInt (blitzwinapi_bankIn,4,position)
	PokeInt (blitzwinapi_bankIn,8,flag)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_RemoveMenu",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

; mouse
Function bGlobalMouseX ()
	Return CallDLL (BWA_DLL_NAME,"WinApi_GlobalMouseX")	
End Function

Function bGlobalMouseY ()
	Return CallDLL (BWA_DLL_NAME,"WinApi_GlobalMouseY")	
End Function

Function bSetMousePos (x%, y%)
	Local blitzwinapi_result, blitzwinapi_bankIn	
	blitzwinapi_bankIn = CreateBank (8)
	PokeInt (blitzwinapi_bankIn,0,x)
	PokeInt (blitzwinapi_bankIn,4,y)	
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_SetCursorPos",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

Function bSetPointer (pointer, hWnd=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (8)
	PokeInt (blitzwinapi_bankIn,0,pointer)
	PokeInt (blitzwinapi_bankIn,4,hWnd)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_SetPointer",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

Function bSetImagePointer (image$, hWnd=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (8+Len(image)+1)
	PokeInt (blitzwinapi_bankIn,0,hwnd)
	PokeInt (blitzwinapi_bankIn,4,Len(image))
	PokeString(blitzwinapi_bankIn,image,8)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_SetImagePointer",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)	
	Return blitzwinapi_result
End Function

Function bSwapMouseButton (swap)
	Local blitzwinapi_result, blitzwinapi_bankIn	
	blitzwinapi_bankIn = CreateBank (4)
	PokeInt (blitzwinapi_bankIn,0,swap)	
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_SwapMouse",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

; system
Function bGetComputerName$ ()
	Local blitzwinapi_result$, blitzwinapi_bankIn, blitzwinapi_bankOut
	blitzwinapi_bankIn = CreateBank ()
	blitzwinapi_bankOut = CreateBank (255)	
	CallDLL (BWA_DLL_NAME,"WinApi_GetComputerName", blitzwinapi_bankIn, blitzwinapi_bankOut)
	blitzwinapi_result = PeekString(blitzwinapi_bankOut,0)
	FreeBank (blitzwinapi_bankIn)	
	FreeBank (blitzwinapi_bankOut)
	Return blitzwinapi_result
End Function

Function bGetUserName$ ()
	Local blitzwinapi_result$, blitzwinapi_bankIn, blitzwinapi_bankOut
	blitzwinapi_bankIn = CreateBank ()
	blitzwinapi_bankOut = CreateBank (255)	
	CallDLL (BWA_DLL_NAME,"WinApi_GetUserName", blitzwinapi_bankIn, blitzwinapi_bankOut)
	blitzwinapi_result = PeekString(blitzwinapi_bankOut,0)
	FreeBank (blitzwinapi_bankIn)	
	FreeBank (blitzwinapi_bankOut)
	Return blitzwinapi_result
End Function

Function bGlobalMemoryStatus ()
	Local blitzwinapi_bankIn, blitzwinapi_bankOut
	blitzwinapi_bankIn = CreateBank (0)
	blitzwinapi_bankOut = CreateBank (28)
	CallDLL (BWA_DLL_NAME,"WinApi_GlobalMemoryStatus",blitzwinapi_bankIn, blitzwinapi_bankOut)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_bankOut
End Function

Function bRAMTotal()
	Local blitzwinapi_result, blitzwinapi_bankOut
	blitzwinapi_bankOut = bGlobalMemoryStatus ()
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,4)
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result
End Function

Function bRAMAvail()
	Local blitzwinapi_result, blitzwinapi_bankOut
	blitzwinapi_bankOut = bGlobalMemoryStatus ()
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,8)
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result
End Function

Function bRAMUsage()
	Local blitzwinapi_result, blitzwinapi_bankOut
	blitzwinapi_bankOut = bGlobalMemoryStatus ()
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,0)
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result
End Function

Function bPageTotal()
	Local blitzwinapi_result, blitzwinapi_bankOut
	blitzwinapi_bankOut = bGlobalMemoryStatus ()
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,12)
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result
End Function

Function bPageAvail()
	Local blitzwinapi_result, blitzwinapi_bankOut
	blitzwinapi_bankOut = bGlobalMemoryStatus ()
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,16)
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result
End Function

Function bVirtualTotal()
	Local blitzwinapi_result, blitzwinapi_bankOut
	blitzwinapi_bankOut = bGlobalMemoryStatus ()
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,20)
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result
End Function

Function bVirtualAvail()
	Local blitzwinapi_result, blitzwinapi_bankOut
	blitzwinapi_bankOut = bGlobalMemoryStatus ()
	blitzwinapi_result = PeekInt (blitzwinapi_bankOut,24)
	FreeBank blitzwinapi_bankOut
	Return blitzwinapi_result
End Function

Function bShell (path$, cmd$ = "", show% = SW_SHOWNORMAL, hWnd = 0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (16+Len(path)+Len(cmd)+2)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	PokeInt (blitzwinapi_bankIn,4,show)
	PokeInt (blitzwinapi_bankIn,8,Len(path))
	PokeInt (blitzwinapi_bankIn,12,Len(cmd))
	PokeString (blitzwinapi_bankIn,path,16)
	PokeString (blitzwinapi_bankIn,cmd,16+Len(path)+1)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_Shell",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

; clipboard
Function bGetClipboardText$ (buffer = 1024, hWnd%=0)
	Local blitzwinapi_result$, blitzwinapi_bankIn, blitzwinapi_bankOut
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (4)		
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	blitzwinapi_bankOut = CreateBank (buffer)
	CallDLL (BWA_DLL_NAME,"WinApi_GetClipboardText",blitzwinapi_bankIn, blitzwinapi_bankOut)
	blitzwinapi_result = PeekString(blitzwinapi_bankOut,0)
	FreeBank (blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankOut)
	Return blitzwinapi_result	
End Function

Function bSetClipboardText (txt$,hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (Len(txt$)+5)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	PokeString(blitzwinapi_bankIn,txt,4)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_SetClipboardText",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)	
	Return blitzwinapi_result	
End Function

; dialogs
Function bRequestColor(r=0, g=0, b=0, hWnd%=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (7)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	PokeByte (blitzwinapi_bankIn,4,r)
	PokeByte (blitzwinapi_bankIn,5,g)
	PokeByte (blitzwinapi_bankIn,6,b)
	CallDLL (BWA_DLL_NAME,"WinApi_ChooseColor",blitzwinapi_bankIn, BWA_COLOR_BANK)
	FreeBank (blitzwinapi_bankIn)
	Return BWA_COLOR_BANK	
End Function 

Function bRequestFont(hWnd%=0)
	Local blitzwinapi_result$, blitzwinapi_bankIn, blitzwinapi_bankOut, font_size, font_bold, font_italic, font_name$, font_underline
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (4)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	blitzwinapi_bankOut = CreateBank (48)
	CallDLL (BWA_DLL_NAME,"WinApi_ChooseFont",blitzwinapi_bankIn, blitzwinapi_bankOut)
	FreeBank (blitzwinapi_bankIn)
	font_name = peekstring (blitzwinapi_bankOut,0)	
	font_size = PeekInt(blitzwinapi_bankOut,32)/10
	If PeekInt(blitzwinapi_bankOut,36)=700 Then
		font_bold = 1
	Else
		font_bold = 0
	End If
	font_italic = PeekInt(blitzwinapi_bankOut,40)
	font_underline = PeekInt(blitzwinapi_bankOut,44)
	FreeBank (blitzwinapi_bankOut)
	blitzwinapi_result = LoadFont (font_name,font_size,font_bold,font_italic,font_underline)
	Return blitzwinapi_result	
End Function

Function bRequestFile$(filter$="All (*.*)|*.*", save=0, hWnd%=0)
	Local blitzwinapi_result$, blitzwinapi_bankIn, blitzwinapi_bankOut
	filter$ = Replace (filter, "|", Chr(0))
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (8+Len(filter)+1)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	PokeInt (blitzwinapi_bankIn,4,save)
	PokeString (blitzwinapi_bankIn, filter, 8)
	blitzwinapi_bankOut = CreateBank (260)
	CallDLL (BWA_DLL_NAME,"WinApi_ChooseFile",blitzwinapi_bankIn, blitzwinapi_bankOut)	
	blitzwinapi_result$ = peekstring (blitzwinapi_bankOut,0)
	FreeBank (blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankOut)
	Return blitzwinapi_result	
End Function

Function bMessageBox(message$,caption$,flag=MB_OK,hWnd=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (16+Len(message)+Len(caption)+2)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	PokeInt (blitzwinapi_bankIn,4,flag)
	PokeInt (blitzwinapi_bankIn,8,Len(message))
	PokeInt (blitzwinapi_bankIn,12,Len(caption))
	PokeString (blitzwinapi_bankIn,message,16)
	PokeString (blitzwinapi_bankIn,caption,16+Len(message)+1)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_MessageBox",blitzwinapi_bankIn,blitzwinapi_bankOut)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

; ini
Function bIniSetValue(section$, key$, value$, ini$="")
	Local blitzwinapi_result, blitzwinapi_bankIn
	If ini = "" Then ini = BWA_INI_FILENAME
	blitzwinapi_bankIn = CreateBank (16+Len(section)+Len(key)+Len(value)+Len(ini)+4)
	PokeInt (blitzwinapi_bankIn,0,Len(section))
	PokeInt (blitzwinapi_bankIn,4,Len(key))
	PokeInt (blitzwinapi_bankIn,8,Len(value))
	PokeInt (blitzwinapi_bankIn,12,Len(ini))
	PokeString (blitzwinapi_bankIn,section,16)
	PokeString (blitzwinapi_bankIn,key,16+Len(section)+1)
	PokeString (blitzwinapi_bankIn,value,16+Len(section)+Len(key)+2)
	PokeString (blitzwinapi_bankIn,ini,16+Len(section)+Len(key)+Len(value)+3)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_IniWriteValue",blitzwinapi_bankIn)	
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

Function bIniGetValue$(section$, key$, default_value$=0, ini$="")
	Local blitzwinapi_result$, blitzwinapi_bankIn, blitzwinapi_bankOut
	If ini = "" Then ini = BWA_INI_FILENAME
	blitzwinapi_bankIn = CreateBank (16+Len(section)+Len(key)+Len(default_value)+Len(ini)+4)
	PokeInt (blitzwinapi_bankIn,0,Len(section))
	PokeInt (blitzwinapi_bankIn,4,Len(key))
	PokeInt (blitzwinapi_bankIn,8,Len(default_value))
	PokeInt (blitzwinapi_bankIn,12,Len(ini))
	PokeString (blitzwinapi_bankIn,section,16)
	PokeString (blitzwinapi_bankIn,key,16+Len(section)+1)
	PokeString (blitzwinapi_bankIn,default_value,16+Len(section)+Len(key)+2)
	PokeString (blitzwinapi_bankIn,ini,16+Len(section)+Len(key)+Len(default_value)+3)
	blitzwinapi_bankOut = CreateBank (256)
	CallDLL (BWA_DLL_NAME,"WinApi_IniReadValue",blitzwinapi_bankIn, blitzwinapi_bankOut)
	blitzwinapi_result$ = PeekString (blitzwinapi_bankOut,0)
	FreeBank (blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankOut)
	Return blitzwinapi_result	
End Function

; reg
Function bRegSetValue(root, subkey$, key$,value$)
	Local blitzwinapi_result, blitzwinapi_bankIn
	blitzwinapi_bankIn = CreateBank (16+Len(subkey)+Len(key)+Len(value)+3)
	PokeInt (blitzwinapi_bankIn,0,root)
	PokeInt (blitzwinapi_bankIn,4,Len(subkey))
	PokeInt (blitzwinapi_bankIn,8,Len(key))
	PokeInt (blitzwinapi_bankIn,12,Len(value))
	PokeString (blitzwinapi_bankIn,subkey,16)
	PokeString (blitzwinapi_bankIn,key,16+Len(subkey)+1)
	PokeString (blitzwinapi_bankIn,value,16+Len(subkey)+Len(key)+2)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_RegWriteValue",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

Function bRegGetValue$(root, subkey$, key$)
	Local blitzwinapi_result$, blitzwinapi_bankIn, blitzwinapi_bankOut
	blitzwinapi_bankIn = CreateBank (12+Len(subkey)+Len(key)+2)
	PokeInt (blitzwinapi_bankIn,0,root)
	PokeInt (blitzwinapi_bankIn,4,Len(subkey))
	PokeInt (blitzwinapi_bankIn,8,Len(key))
	PokeString (blitzwinapi_bankIn,subkey,12)
	PokeString (blitzwinapi_bankIn,key,12+Len(subkey)+1)
	blitzwinapi_bankOut = CreateBank (256)
	CallDLL (BWA_DLL_NAME,"WinApi_RegReadValue",blitzwinapi_bankIn,blitzwinapi_bankOut)
	blitzwinapi_result = PeekString(blitzwinapi_bankOut,0)
	FreeBank (blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankOut)
	Return blitzwinapi_result	
End Function

Function bRegDeleteKey(root, subkey$)
	Local blitzwinapi_result$, blitzwinapi_bankIn
	blitzwinapi_bankIn = CreateBank (8+Len(subkey)+1)
	PokeInt (blitzwinapi_bankIn,0,root)
	PokeInt (blitzwinapi_bankIn,4,Len(subkey))
	PokeString (blitzwinapi_bankIn,subkey,8)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_RegDeleteKey",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

Function bRegDeleteValue(root, subkey$, key$)
	Local blitzwinapi_result$, blitzwinapi_bankIn
	blitzwinapi_bankIn = CreateBank (12+Len(subkey)+Len(key)+2)
	PokeInt (blitzwinapi_bankIn,0,root)
	PokeInt (blitzwinapi_bankIn,4,Len(subkey))
	PokeInt (blitzwinapi_bankIn,8,Len(key))
	PokeString (blitzwinapi_bankIn,subkey,12)
	PokeString (blitzwinapi_bankIn,key,12+Len(subkey)+1)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_RegDeleteValue",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

; events
Function bGetEvent(hwnd = 0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (4)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_GetEvent",blitzwinapi_bankIn, BWA_MESSAGE_BANK)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

Function bSendMessage (hwnd, message, wParam=0, lParam=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (16)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	PokeInt (blitzwinapi_bankIn,4,message)
	PokeInt (blitzwinapi_bankIn,8,wParam)
	PokeInt (blitzwinapi_bankIn,12,lParam)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_SendMessage",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

Function bWaitEvent()
	blitzwinapi_bankIn = CreateBank (0)	
	Return CallDLL (BWA_DLL_NAME,"WinApi_WaitEvent",blitzwinapi_bankIn,BWA_MESSAGE_BANK)	
End Function

Function bQueryDrop$()
	Local blitzwinapi_result$, blitzwinapi_bankIn, blitzwinapi_bankOut
	blitzwinapi_bankIn = CreateBank (0)
	blitzwinapi_bankOut = CreateBank (260)
	CallDLL (BWA_DLL_NAME,"WinApi_GetDrop",blitzwinapi_bankIn,blitzwinapi_bankOut)
	blitzwinapi_result = PeekString(blitzwinapi_bankOut,0)
	FreeBank (blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankOut)
	Return blitzwinapi_result	
End Function

Function bWaitDrop$ (wait=10)
	Local drop_time, key, out,drop$	
	key = bGetKey($1)
	If key =(-127) Or key =(-128) Then
		out = 0
		If bGlobalMouseX()< bGetWindowX() Or bGlobalMouseY()< bGetWindowY() Then out=1
		If bGlobalMouseX()> bGetWindowRight() Or bGlobalMouseY()> bGetWindowBottom() Then out=1
		If Not out Then		
			If BWA_DRAG_START =1 Then
				For i=1 To wait
					bShowWindow ()				
					drop = bQueryDrop()									
					If drop <> "" Exit
					If MouseHit (1) Exit
				Next
				BWA_DRAG_START = 0
			End If
		Else
			BWA_DRAG_START = 1		
		End If
	Else
		BWA_DRAG_START = 0
	End If
	Return drop$	
End Function

Function bGetKey (key)	
	Local blitzwinapi_result, blitzwinapi_bankIn
	blitzwinapi_bankIn = CreateBank (4)
	PokeInt (blitzwinapi_bankIn,0,key)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_GetKey",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

; gui
; menu
Function bCreatePopupMenu ()
	Return  CallDLL (BWA_DLL_NAME,"WinApi_CreatePopupMenu", blitzwinapi_bankIn)	
End Function

Function bRequestPopupMenu (menu, hwnd=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (8)
	PokeInt (blitzwinapi_bankIn,0,menu)
	PokeInt (blitzwinapi_bankIn,4,hWnd)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_DrawPopupMenu", blitzwinapi_bankIn)	
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

Function bRequestMenu (menu, hwnd=0)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (8)
	PokeInt (blitzwinapi_bankIn,0,menu)
	PokeInt (blitzwinapi_bankIn,4,hWnd)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_RequestMenu", blitzwinapi_bankIn)	
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

Function bDestroyMenu (menu)
	Local blitzwinapi_result, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (4)
	PokeInt (blitzwinapi_bankIn,0,menu)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_DestroyMenu", blitzwinapi_bankIn)	
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function

Function bAddMenuItem (menu,id,name$,class=MF_ENABLED)
	Local blitzwinapi_result, blitzwinapi_bankIn
	blitzwinapi_bankIn = CreateBank (16+Len(name)+1)
	PokeInt (blitzwinapi_bankIn,0,menu)
	PokeInt (blitzwinapi_bankIn,4,id)
	PokeInt (blitzwinapi_bankIn,8,class)
	PokeInt (blitzwinapi_bankIn,12,Len(name))
	PokeString (blitzwinapi_bankIn,name,16)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_AddMenuItem", blitzwinapi_bankIn)
	bDrawMenuBar (hWnd)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bAddPopupItem (menu,name$)
	Local result
	result = bCreatePopupMenu()
	bAddMenuItem (menu,result,name,MF_POPUP)
	Return result
End Function

Function bEnableMenuItem (menu,id)
	Local blitzwinapi_result, blitzwinapi_bankIn
	blitzwinapi_bankIn = CreateBank (12)
	PokeInt (blitzwinapi_bankIn,0,menu)
	PokeInt (blitzwinapi_bankIn,4,id)
	PokeInt (blitzwinapi_bankIn,8,MF_ENABLED)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_EnableMenuItem", blitzwinapi_bankIn)	
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bDisableMenuItem (menu,id)
	Local blitzwinapi_result, blitzwinapi_bankIn
	blitzwinapi_bankIn = CreateBank (12)
	PokeInt (blitzwinapi_bankIn,0,menu)
	PokeInt (blitzwinapi_bankIn,4,id)
	PokeInt (blitzwinapi_bankIn,8,MF_DISABLED)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_EnableMenuItem", blitzwinapi_bankIn)	
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bCheckMenuItem (menu,id)
	Local blitzwinapi_result, blitzwinapi_bankIn
	blitzwinapi_bankIn = CreateBank (12)
	PokeInt (blitzwinapi_bankIn,0,menu)
	PokeInt (blitzwinapi_bankIn,4,id)
	PokeInt (blitzwinapi_bankIn,8,MF_CHECKED)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_CheckMenuItem", blitzwinapi_bankIn)	
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bUncheckMenuItem (menu,id)
	Local blitzwinapi_result, blitzwinapi_bankIn
	blitzwinapi_bankIn = CreateBank (12)
	PokeInt (blitzwinapi_bankIn,0,menu)
	PokeInt (blitzwinapi_bankIn,4,id)
	PokeInt (blitzwinapi_bankIn,8,MF_UNCHECKED)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_CheckMenuItem", blitzwinapi_bankIn)	
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bGetMenuItemState (menu,id)
	Local blitzwinapi_result, blitzwinapi_bankIn
	blitzwinapi_bankIn = CreateBank (8)
	PokeInt (blitzwinapi_bankIn,0,menu)
	PokeInt (blitzwinapi_bankIn,4,id)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_MenuItemState", blitzwinapi_bankIn)	
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result
End Function

Function bAddWindowToTray (tip$, hWnd=0)
	Local blitzwinapi_result$, blitzwinapi_bankIn
	If hWnd = 0 Then hWnd = BWA_CURRENT_HWND
	blitzwinapi_bankIn = CreateBank (8+Len(tip)+1)
	PokeInt (blitzwinapi_bankIn,0,hWnd)
	PokeInt (blitzwinapi_bankIn,4,Len(tip))
	PokeString (blitzwinapi_bankIn,tip,8)
	blitzwinapi_result = CallDLL (BWA_DLL_NAME,"WinApi_AddToTray",blitzwinapi_bankIn)
	FreeBank (blitzwinapi_bankIn)
	Return blitzwinapi_result	
End Function


; additional functions
Function PokeString(mBankAddr,sStringOut$,iBufferOffset = 0)	
	For n = 1 To Len(sStringOut$)
		PokeByte mBankAddr,iBufferOffset,Asc(Mid$(sStringOut$,n,1))
		iBufferOffset = iBufferOffset + 1		
	Next
	PokeByte mBankAddr,iBufferOffset,0 ; Null terminate
End Function

Function PeekString$(mBankAddr,iBufferOffset = 0)
	Local sOutStr$ = "",iByte
	For n = 0 To BankSize(mBankAddr)-1		
		iByte = PeekByte(mBankAddr,iBufferOffset)
		If iByte <> 0			
			sOutStr$ = sOutStr$ + Chr(iByte)
		Else
			Exit
		EndIf
		iBufferOffset = iBufferOffset + 1
	Next
	Return sOutStr$
End Function
