#include <windows.h> 

typedef BOOL (CALLBACK* SETLAYEREDWINDOWATTRIBUTES)(HWND, COLORREF, BYTE, long);

// дескрипторы окна
extern "C" __declspec(dllexport) HWND WinApi_GetActiveWindow() 
{	
	return GetActiveWindow();
}

extern "C" __declspec(dllexport) HWND WinApi_FindWindow(const void *in,int in_size) 
{	
	char caption[1024];
	ZeroMemory (&caption, sizeof(caption));
	for (int i=0; i < 1024; i++)
	{
		caption[i] = *(char *)in;
		in = (BYTE *)in+sizeof(char);
	}
	return FindWindow(NULL, caption);
}

extern "C" __declspec(dllexport) HWND WinApi_GetDesktop() 
{
    return GetDesktopWindow();
}

extern "C" __declspec(dllexport) HWND WinApi_WindowFromPoint(const void *in,int in_size)
{
	tagPOINT mouse;
	mouse.x = *(int *)in;
	in = (int *)in+1;
	mouse.y = *(int *)in;
	return WindowFromPoint(mouse);	
}

// окна
extern "C" __declspec(dllexport) HWND WinApi_SetActiveWindow(const void *in,int in_size)
{
	HWND window = *(HWND *)in;	
	SetForegroundWindow(window);
	return SetActiveWindow(window);	
}

extern "C" __declspec(dllexport) int WinApi_HideWindow(const void *in,int in_size)
{
	HWND window = *(HWND *)in;	
	return ShowWindow(window,SW_HIDE);	
}

extern "C" __declspec(dllexport) int WinApi_ShowWindow(const void *in,int in_size)
{
	HWND window = *(HWND *)in;
	SetForegroundWindow(window);
	return ShowWindow(window,SW_SHOW);	
}

extern "C" __declspec(dllexport) int WinApi_FlashWindow(const void *in,int in_size)
{
	HWND window = *(HWND *)in;
	in = (int *)in + 1;
	int flash = *(int *)in;
	return FlashWindow(window,flash);	
}

extern "C" __declspec(dllexport) int WinApi_GetWindowRect(const void *in,int in_size,void *out,int out_size)
{
	HWND window = *(HWND *)in;
	int result;
	tagRECT *pPosition;
	tagRECT position;
	pPosition = &position;
	result = GetWindowRect(window,pPosition);
	out = (int *)out;
	(*(int *)out) = position.left;
	out = (int *)out + 1;
	(*(int *)out) = position.right;
	out = (int *)out + 1;
	(*(int *)out) = position.top;
	out = (int *)out + 1;
	(*(int *)out) = position.bottom;
	return result;	
}

extern "C" __declspec(dllexport) int WinApi_SetRectRegion(const void *in,int in_size)
{
	HWND window = *(HWND *)in;
	in = (int *)in + 1;
	HRGN rect = *(HRGN *)in;
	return SetWindowRgn(window, rect, TRUE);	
}

extern "C" __declspec(dllexport) HRGN WinApi_CreateRectRegion(const void *in,int in_size)
{
	int left = *(int *)in;
	in = (int *)in + 1;
	int top = *(int *)in;
	in = (int *)in + 1;
	int right = *(int *)in;
	in = (int *)in + 1;
	int bottom = *(int *)in;
	return CreateRectRgn(left, top, right, bottom);	
}



//дескрипторы меню
extern "C" __declspec(dllexport) HMENU WinApi_GetSystemMenu(const void *in,int in_size)
{
	HWND window = *(HWND *)in;
	in = (int *)in+1;
	int bRevert = *(int *)in;
	return GetSystemMenu(window,bRevert);	
} 

//меню
extern "C" __declspec(dllexport) int WinApi_GetMenuItemCount(const void *in,int in_size)
{
	HMENU menu = *(HMENU *)in;
	return GetMenuItemCount(menu);	
}

extern "C" __declspec(dllexport) BYTE WinApi_DrawMenuBar(const void *in,int in_size)
{
	HWND window = *(HWND *)in;
	return DrawMenuBar(window);	
}

extern "C" __declspec(dllexport) BYTE WinApi_RemoveMenu(const void *in,int in_size)
{
	HMENU menu = *(HMENU *)in;
	in = (int *)in+1;
	unsigned int position = *(unsigned int *)in;
	in = (int *)in+1;
	unsigned int flag = *(unsigned int *)in;
	return RemoveMenu(menu,position,flag);	
}

// стили окна
extern "C" __declspec(dllexport) long WinApi_GetWindowLong(const void *in,int in_size)
{
	HWND window = *(HWND *)in;
	in = (int *)in+1;
	int index = *(int *)in;
	return GetWindowLong(window,index);	
}

extern "C" __declspec(dllexport) long WinApi_SetWindowLong(const void *in,int in_size)
{
	HWND window = *(HWND *)in;
	in = (int *)in+1;
	int index = *(int *)in;
	in = (long *)in+1;
	int newlong = *(long *)in;
	return SetWindowLong(window,index,newlong);	
}

extern "C" __declspec(dllexport) long WinApi_SetLayeredWindowAttributes(const void *in,int in_size)
{
	HMODULE hDll;
	SETLAYEREDWINDOWATTRIBUTES pLayered;
	hDll = LoadLibrary( "user32.dll" );
	pLayered = (SETLAYEREDWINDOWATTRIBUTES)GetProcAddress (hDll,"SetLayeredWindowAttributes");
	HWND window = *(HWND *)in;
	in = (COLORREF *)in+1;
	COLORREF crKey = *(COLORREF *)in;
	in = (int *)in+1;
	BYTE alpha = *(BYTE *)in;	
	in = (long *)in+1;
	long flag = *(long *)in;
	long bResult = pLayered ((HWND) window,	(COLORREF) crKey,(BYTE) alpha,(long) flag);
	FreeLibrary( hDll );
	return bResult;
}

// мышь
extern "C" __declspec(dllexport) int WinApi_GlobalMouseX()
{
	tagPOINT *pMouse;
	tagPOINT mouse;
	pMouse = &mouse;
	GetCursorPos (pMouse);
	mouse = *(tagPOINT *)pMouse;
	return mouse.x;	
}

extern "C" __declspec(dllexport) int WinApi_GlobalMouseY()
{
	tagPOINT *pMouse;
	tagPOINT mouse;
	pMouse = &mouse; 
	GetCursorPos (pMouse);
	mouse = *(tagPOINT *)pMouse;
	return mouse.y;	
}

extern "C" __declspec(dllexport) int WinApi_SetCursorPos(const void *in,int in_size)
{
	int x = *(int *)in;
	in = (int *)in+1;
	int y = *(int *)in;	
	return SetCursorPos (x,y);
}


extern "C" __declspec(dllexport) int WinApi_SwapMouse(const void *in,int in_size)
{
	int swap = *(int *)in;
	return SwapMouseButton (swap);
}

extern "C" __declspec(dllexport) int WinApi_SetPointer(const void *in,int in_size)
{
	int pointer = *(int *)in;
	in = (int *)in+1;
	HWND window = *(HWND *)in;
	HCURSOR cursor;
	ZeroMemory(&cursor,sizeof(cursor));
	DestroyCursor(GetCursor());
	cursor = LoadCursor(0,MAKEINTRESOURCE(pointer));
	if (!SetClassLong(window,GCL_HCURSOR,(LONG)cursor)) return 0;
	DrawMenuBar(window);
	return 1;
}

extern "C" __declspec(dllexport) int WinApi_SetImagePointer(const void *in,int in_size)
{
	char pointer[260];
	HWND window = *(HWND *)in;
	in = (int *)in+1;
	int len_name = *(int *)in;
	in = (int *)in+1;
	ZeroMemory(&pointer,sizeof(pointer));
	for (int i=0; i<len_name; i++)
	{
		pointer[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	HCURSOR cursor;
	ZeroMemory(&cursor,sizeof(cursor));
	DestroyCursor(GetCursor());
	cursor = LoadCursorFromFile(pointer);
	if (!cursor) return 0;
	if (!SetClassLong(window,GCL_HCURSOR,(LONG)cursor)) return 0;
	DrawMenuBar(window);
	return 1;
}


// система
extern "C" __declspec(dllexport) BYTE WinApi_GetComputerName(const void *in,int in_size,void *out,int out_sz )
{
	char ComputerName[MAX_COMPUTERNAME_LENGTH + 1];
	unsigned long len_ComputerName = MAX_COMPUTERNAME_LENGTH + 1;
	int comp = GetComputerName(ComputerName,&len_ComputerName);	
	out = (BYTE *)out;
	for (int i=0; i<MAX_COMPUTERNAME_LENGTH + 2; i++)
	{
		if (!ComputerName[i]) break;		
		(*(BYTE *)out) = ComputerName[i];
		out = (BYTE *)out+sizeof(char);
	}
	
	return comp;
}

extern "C" __declspec(dllexport) BYTE WinApi_GetUserName(const void *in,int in_size,void *out,int out_sz )
{
	char UserName[255];
	unsigned long len_ComputerName = 255;
	int user = GetUserNameA(UserName,&len_ComputerName);	
	out = (BYTE *)out;
	for (int i=0; i<MAX_COMPUTERNAME_LENGTH + 2; i++)
	{
		if (!UserName[i]) break;		
		(*(BYTE *)out) = UserName[i];
		out = (BYTE *)out+sizeof(char);
	}
	
	return user;
}

extern "C" __declspec(dllexport) BYTE WinApi_GlobalMemoryStatus(const void *in,int in_size,void *out,int out_sz )
{
	MEMORYSTATUS memory;
	ZeroMemory (&memory,sizeof(memory));
	GlobalMemoryStatus(&memory);		
	(*(int *)out) = memory.dwMemoryLoad;
	out = (int *)out+1;	
	(*(int *)out) = memory.dwTotalPhys;
	out = (int *)out+1;	
	(*(int *)out) = memory.dwAvailPhys;
	out = (int *)out+1;	
	(*(int *)out) = memory.dwTotalPageFile;
	out = (int *)out+1;	
	(*(int *)out) = memory.dwAvailPageFile;
	out = (int *)out+1;	
	(*(int *)out) = memory.dwTotalVirtual;
	out = (int *)out+1;	
	(*(int *)out) = memory.dwAvailVirtual;
	out = (int *)out+1;
	return 1;
}

extern "C" __declspec(dllexport) HINSTANCE WinApi_Shell(const void *in,int in_size)
{
	HWND window = *(HWND *)in;
	in = (int *)in+1;
	int show = *(int *)in;
	in = (int *)in+1;
	int path_len = *(int *)in;
	in = (int *)in+1;
	int cmd_len = *(int *)in;
	in = (int *)in+1;
	char path[260];
	char cmd[1024];
	ZeroMemory(&path, sizeof(path));
	ZeroMemory(&cmd, sizeof(cmd));
	for (int i=0; i<path_len+1; i++)
	{
		path[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	for (i=0; i<cmd_len+1; i++)
	{
		cmd[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	return ShellExecute (window, NULL, path, cmd,NULL, show);
}


// буфер обмена
extern "C" __declspec(dllexport) int WinApi_GetClipboardText(const void *in,int in_size,void *out,int out_sz )
{
	if (!EmptyClipboard()) 
	{
	HWND window = *(HWND *)in;
	LPVOID  clipboard;
	if (!IsClipboardFormatAvailable(CF_TEXT)) return 0;
    if (!OpenClipboard(window)) return 0;
	HGLOBAL hglb = GetClipboardData(CF_TEXT);
	out = (BYTE *)out;
	if (hglb != NULL)
        {
            clipboard = GlobalLock(hglb);
            if (clipboard != NULL)
            {              
			 for (int i=0; i<out_sz; i++) 
			 { 
				if (*(BYTE *)clipboard != 0) 
				{
				 (*(BYTE *)out) = *(BYTE *)clipboard;
				 out = (BYTE *)out+sizeof(char);
				 clipboard = (BYTE *)clipboard+sizeof(char);
				}
				else break;
			 }			
             GlobalUnlock(hglb);
            }
        }	
	CloseClipboard();
	return 1;
	}
	else return 0;	
}

extern "C" __declspec(dllexport) int WinApi_SetClipboardText(const void *in,int in_size)
{
	HWND window = *(HWND *)in;
	LPVOID  clipboard;
	int sumbyte = 0;
    if (!OpenClipboard(window)) return 0;
	if (!EmptyClipboard()) return 0;
	HGLOBAL hglb = GlobalAlloc(GMEM_MOVEABLE, in_size);
	if (!hglb) {
		CloseClipboard();		
		return 0;
	}
	in = (BYTE *)in+sizeof(char)*4;
	clipboard = GlobalLock(hglb);
	if (!clipboard) 
	{
		CloseClipboard();		
		return 0;
	} 
	for (int i=0; i < in_size-4; i++) 
	{ 
		(*(BYTE *)clipboard) = *(BYTE *)in;
		in = (BYTE *)in+sizeof(char);
		clipboard = (BYTE *)clipboard+sizeof(char);
	}
	GlobalUnlock(hglb);
	if (!SetClipboardData(CF_TEXT, hglb))
	{
		CloseClipboard();
		return 0;
	}
	CloseClipboard();
	return i;
}

// диалоги
extern "C" __declspec(dllexport) int WinApi_ChooseColor(const void *in,int in_size,void *out,int out_sz)
{
	CHOOSECOLOR cc;
	HWND window = *(HWND *)in; 
	in = (int *)in+1;
	in = (BYTE *)in;
	BYTE r = *(BYTE *)in;
	in = (BYTE *)in+sizeof(BYTE);
	BYTE g = *(BYTE *)in;
	in = (BYTE *)in+sizeof(BYTE);
	BYTE b = *(BYTE *)in;
	COLORREF color = RGB(r,g,b);
	static COLORREF acrCustClr[32];	
	ZeroMemory(&cc, sizeof(cc));
	cc.lStructSize = sizeof(cc);
	cc.hwndOwner = window;
	cc.lpCustColors = (LPDWORD) acrCustClr;
	cc.rgbResult = color;
	cc.Flags = CC_FULLOPEN | CC_RGBINIT;
	out = (BYTE *)out;
	if (!ChooseColor(&cc)) return 0;
	(*(BYTE *)out) = GetRValue (cc.rgbResult);
	out = (BYTE *)out+sizeof(BYTE);
	(*(BYTE *)out) = GetGValue (cc.rgbResult);
	out = (BYTE *)out+sizeof(BYTE);
	(*(BYTE *)out) = GetBValue (cc.rgbResult);
	return 1;
}

extern "C" __declspec(dllexport) int WinApi_ChooseFont(const void *in,int in_size,void *out,int out_sz)
{
	CHOOSEFONT cf;
	HWND window = *(HWND *)in;
	static LOGFONT lf;
	ZeroMemory(&cf, sizeof(cf));
	cf.lStructSize = sizeof (cf);
	cf.hwndOwner = window;
	cf.lpLogFont = &lf;
	cf.Flags = CF_SCREENFONTS | CF_EFFECTS;
	if (!ChooseFont(&cf)) return 0;
	out = (char *)out;
	for (int i=0; i<32; i++)
	{
		(*(char *)out) = lf.lfFaceName[i];
		out = (char *)out+sizeof(char);
	}
	(*(int *)out) = cf.iPointSize;
	out = (int *)out+1;
	(*(int *)out) = lf.lfWeight;
	out = (int *)out+1;
	(*(int *)out) = lf.lfItalic;
	out = (int *)out+1;
	(*(int *)out) = lf.lfUnderline;
	return 1;
}

extern "C" __declspec(dllexport) int WinApi_ChooseFile(const void *in,int in_size,void *out,int out_sz)
{
	OPENFILENAME ofn;       
	char szFile[260];       
	HWND window = *(HWND *)in; 
	in = (int *)in+1;
	int save = *(int *)in;
	in = (int *)in+1;
	ZeroMemory(&ofn, sizeof(ofn));
	ZeroMemory(&szFile, sizeof(szFile));
	ofn.lStructSize = sizeof(ofn);
	ofn.hwndOwner = window;
	ofn.lpstrFile = szFile;
	ofn.nMaxFile = sizeof(szFile);
	char filter[256];
	ZeroMemory(&filter, sizeof(filter));
	in = (byte *)in;
	for (int i=0; i<256; i++)
	{
		filter[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	ofn.lpstrFilter = filter;
	ofn.nFilterIndex = 1;
	ofn.lpstrFileTitle = NULL;
	ofn.nMaxFileTitle = 0;
	ofn.lpstrInitialDir = NULL;
	ofn.Flags = OFN_PATHMUSTEXIST | OFN_FILEMUSTEXIST;
	if (!save)
	{
		GetOpenFileName(&ofn);
	}
	else
	{
		GetSaveFileName(&ofn);
	}
	for (i=0; i<260; i++)
	{
		(*(char *)out) = ofn.lpstrFile[i];
		out = (char *)out+sizeof(char);
	}
	return 1;
}

extern "C" __declspec(dllexport) int WinApi_MessageBox(const void *in,int in_size)
{
	HWND window = *(HWND *)in;
	in = (int *)in+1;
	int flag = *(int *)in;
	in = (int *)in+1;
	int m_len = *(int *)in;
	in = (int *)in+1;
	int c_len = *(int *)in;
	in = (int *)in+1;
	char caption[256];
	char message[1024];
	ZeroMemory (&caption, sizeof(caption));
	ZeroMemory (&message, sizeof(message));
	for (int i=0; i<m_len+1; i++)
	{
		message[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	for (i=0; i< c_len+1; i++)
	{
		caption[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	return MessageBox(window,message,caption,flag);
}

 // ини

extern "C" __declspec(dllexport) int WinApi_IniWriteValue(const void *in,int in_size)
{
	int len_section = *(int *)in; 
	in = (int *)in+1;
	int len_key = *(int *)in; 
	in = (int *)in+1;
	int len_value = *(int *)in; 
	in = (int *)in+1;
	int len_ini = *(int *)in; 
	in = (int *)in+1;	
	char section[256];
	char key[256];
	char value[256];
	char ini[260];	
	for (int i=0; i<len_section+1; i++)
	{
		section[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	for (i=0; i<len_key+1; i++)
	{
		key[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	for (i=0; i<len_value+1; i++)
	{
		value[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	for (i=0; i<len_ini+1; i++)
	{
		ini[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}	
	return WritePrivateProfileString (section,key,value,ini);
}

extern "C" __declspec(dllexport) int WinApi_IniReadValue(const void *in,int in_size,void *out,int out_sz)
{
	int len_section = *(int *)in; 
	in = (int *)in+1;
	int len_key = *(int *)in; 
	in = (int *)in+1;
	int len_default_value = *(int *)in; 
	in = (int *)in+1;
	int len_ini = *(int *)in; 
	in = (int *)in+1;	
	char section[256];
	char key[256];
	char value[256];
	char ini[260];
	char default_value[256];
	for (int i=0; i<len_section+1; i++)
	{
		section[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	for (i=0; i<len_key+1; i++)
	{
		key[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	for (i=0; i<len_default_value+1; i++)
	{
		default_value[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	for (i=0; i<len_ini+1; i++)
	{
		ini[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}	
	if (!GetPrivateProfileString(section,key,default_value,value,256,ini)) return 0;
	for (i=0; i<256; i++)
	{
		(*(char *)out) = value[i];
		out = (char *)out+sizeof(char);
	}
	return 1;
}

// реестр
extern "C" __declspec(dllexport) int WinApi_RegWriteValue(const void *in,int in_size)
{
	HKEY root = *(HKEY *)in;
	HKEY hKey;
	DWORD nDisp;
	in = (int *)in+1;
	int len_subkey = *(int *)in; 
	in = (int *)in+1;
	int len_key = *(int *)in; 
	in = (int *)in+1;
	int len_value = *(int *)in; 
	in = (int *)in+1;
	char subkey[256];
	char key[256];
	char value[256];
	ZeroMemory(&value, sizeof(value));
	ZeroMemory(&key, sizeof(key));
	ZeroMemory(&subkey, sizeof(subkey));
	for (int i=0; i<len_subkey+1; i++)
	{
		subkey[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	for (i=0; i<len_key+1; i++)
	{
		key[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	
	for (i=0; i<len_value+1; i++)
	{
		value[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}	
	if (RegCreateKeyEx(root,subkey,0,NULL, REG_OPTION_NON_VOLATILE,KEY_ALL_ACCESS,NULL,&hKey,&nDisp)) return 0;
	if (RegSetValueEx (hKey, key, 0, REG_SZ, (BYTE *)&value, sizeof(value))) return 0;	
	if (RegCloseKey (hKey)) return 0;
	return 1;
}

extern "C" __declspec(dllexport) int WinApi_RegReadValue(const void *in,int in_size,void *out,int out_sz)
{
	HKEY root = *(HKEY *)in;
	HKEY hKey;
	DWORD nSize= 0x256;
	in = (int *)in+1;
	int len_subkey = *(int *)in; 
	in = (int *)in+1;
	int len_key = *(int *)in; 
	in = (int *)in+1;
	char subkey[256];
	char key[256];
	char value[256];
	ZeroMemory(&value, sizeof(value));
	ZeroMemory(&key, sizeof(key));
	ZeroMemory(&subkey, sizeof(subkey));
	for (int i=0; i<len_subkey+1; i++)
	{
		subkey[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	for (i=0; i<len_key+1; i++)
	{
		key[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	if (RegOpenKeyEx(root,subkey,0,KEY_READ,&hKey)) return 0;
	if (RegQueryValueEx(hKey,key,0,0,(LPBYTE)value,&nSize)) return 0;
	for (i=0; i<256; i++)
	{
		(*(char *)out) = value[i];
		out = (char *)out+sizeof(char);
	}
	if (RegCloseKey (hKey)) return 0;
	return 1;
}

extern "C" __declspec(dllexport) int WinApi_RegDeleteKey(const void *in,int in_size,void *out,int out_sz)
{
	HKEY root = *(HKEY *)in;
	in = (int *)in+1;
	int len_subkey = *(int *)in; 
	in = (int *)in+1;
	char subkey[256];
	ZeroMemory(&subkey, sizeof(subkey));
	
	for (int i=0; i<len_subkey+1; i++)
	{
		subkey[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	if (RegDeleteKey(root,subkey)) return 0;
	return 1;
}


extern "C" __declspec(dllexport) int WinApi_RegDeleteValue(const void *in,int in_size,void *out,int out_sz)
{
	HKEY root = *(HKEY *)in;
	HKEY hKey;
	in = (int *)in+1;
	int len_subkey = *(int *)in; 
	in = (int *)in+1;
	int len_key = *(int *)in; 
	in = (int *)in+1;
	char subkey[256];
	char key[256];
	ZeroMemory(&subkey, sizeof(subkey));
	ZeroMemory(&key, sizeof(key));
	for (int i=0; i<len_subkey+1; i++)
	{
		subkey[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	for (i=0; i<len_key+1; i++)
	{
		key[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	if (RegOpenKeyEx(root,subkey,0,KEY_ALL_ACCESS,&hKey)) return 0;
	if (RegDeleteValue(hKey,key)) return 0;
	return 1;
}


// действия
extern "C" __declspec(dllexport) int WinApi_GetEvent(const void *in,int in_size, void *out,int out_sz)
{
	MSG event;	
	HWND window = *(HWND *)in;
	ZeroMemory(&event, sizeof(event));	
	Sleep (1);
	if (PeekMessage (&event,window,NULL,NULL,PM_NOREMOVE|PM_NOYIELD)==1)		
	{
		TranslateMessage(&event);
        DispatchMessage(&event);
		(*(UINT *)out) = event.wParam;
		out = (UINT *)out+1;
		(*(UINT *)out) = event.lParam;
		out = (UINT *)out+1;
		return event.message ; 
	}
	return 0;
}

extern "C" __declspec(dllexport) int WinApi_SendMessage(const void *in,int in_size, void *out,int out_sz)
{
	UINT message;	
	HWND window = *(HWND *)in;	
	in = (int *)in+1;
	message = *(UINT *)in;
	in = (int *)in+1;
	UINT wParam = *(UINT *)in;
	in = (int *)in+1;
	UINT lParam = *(UINT *)in;
	return PostMessage(window, message, wParam, lParam);
}

extern "C" __declspec(dllexport) int WinApi_WaitEvent(const void *in,int in_size, void *out,int out_sz)
{
	MSG event;	
	ZeroMemory(&event, sizeof(event));
	Sleep (1);	
	WaitMessage();
	PeekMessage (&event,NULL,NULL,NULL,PM_NOREMOVE|PM_NOYIELD);
	TranslateMessage(&event);
    DispatchMessage(&event);
	(*(UINT *)out) = event.wParam;
	out = (UINT *)out+1;
	(*(UINT *)out) = event.lParam;
	out = (UINT *)out+1;
	return event.message;
}


extern "C" __declspec(dllexport) int WinApi_GetDrop(const void *in,int in_size,void *out,int out_sz)
{
	MSG event;
	HDROP drop;
	char file[260];
	ZeroMemory(&event, sizeof(event));	
	Sleep (15);
	WaitMessage();
	if (PeekMessage (&event,NULL,563,563,PM_REMOVE|PM_NOYIELD) ==1)		
	{
		if (event.message == WM_DROPFILES)
		{
			drop = (HDROP)event.wParam;
			DragQueryFile(drop,NULL,file,260);
			for (int i=0; i<260; i++)
			{
				(*(char *)out) = file[i];
				out = (char *)out+sizeof(char);
			}
			DragFinish(drop);
			return  1;
		}
	}
	return 0;
}


extern "C" __declspec(dllexport) int WinApi_GetKey(const void *in,int in_size)
{
	int key;
	key = *(int *)in;
	return GetKeyState(key);
}

// гуи

// меню
extern "C" __declspec(dllexport) HMENU WinApi_CreatePopupMenu(const void *in,int in_size)
{
	HMENU menu;
	HWND window = *(HWND *)in;
	menu = CreatePopupMenu();
	return menu;
}

extern "C" __declspec(dllexport) UINT WinApi_AddMenuItem(const void *in,int in_size)
{
	HMENU menu = *(HMENU *)in;
	in = (int *)in+1;
	int id = *(int *)in;
	in = (int *)in+1;
	int type = *(int *)in;
	in = (int *)in+1;
	int len_name = *(int *)in;
	in = (int *)in+1;
	char name[64];
	ZeroMemory(&name,sizeof(name));
	for (int i=0; i<len_name+1; i++)
	{
		name[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}
	int num = GetMenuItemCount(menu);
	InsertMenu(menu,num,type,id,name);
	return GetMenuItemID(menu,num);
}

extern "C" __declspec(dllexport) int WinApi_DrawPopupMenu(const void *in,int in_size)
{
	HMENU menu = *(HMENU *)in;
	in = (int *)in+1;
	HWND window = *(HWND *)in;
	POINT mouse;
	MSG event;
	ZeroMemory(&mouse,sizeof(mouse));
	ZeroMemory(&event,sizeof(event));
	GetCursorPos(&mouse);
	TrackPopupMenu(menu,TPM_LEFTALIGN,mouse.x ,mouse.y,0,window,NULL);
	WaitMessage();
	PeekMessage (&event,window,NULL,NULL,PM_NOREMOVE|PM_NOYIELD);
	TranslateMessage(&event);
    DispatchMessage(&event);
	return LOWORD(event.wParam);
}

extern "C" __declspec(dllexport) HMENU WinApi_CreateMenu(const void *in,int in_size)
{
	HWND window = *(HWND *)in;
	HMENU menu = CreateMenu();
	SetMenu(window,menu);
	return menu;
}

extern "C" __declspec(dllexport) int WinApi_DestroyMenu(const void *in,int in_size)
{
	HMENU menu = *(HMENU *)in;	
	return DestroyMenu(menu);
}

extern "C" __declspec(dllexport) UINT WinApi_EnableMenuItem(const void *in,int in_size)
{
	HMENU menu = *(HMENU *)in;
	in = (int *)in+1;
	int id = *(int *)in;
	in = (int *)in+1;
	int type = *(int *)in;
	in = (int *)in+1;	
	return EnableMenuItem(menu,id,type);
}

extern "C" __declspec(dllexport) UINT WinApi_CheckMenuItem(const void *in,int in_size)
{
	HMENU menu = *(HMENU *)in;
	in = (int *)in+1;
	int id = *(int *)in;
	in = (int *)in+1;
	int type = *(int *)in;
	in = (int *)in+1;	
	return CheckMenuItem(menu,id,type);
}

extern "C" __declspec(dllexport) UINT WinApi_MenuItemState(const void *in,int in_size)
{
	HMENU menu = *(HMENU *)in;
	in = (int *)in+1;
	int id = *(int *)in;
	in = (int *)in+1;	
	return GetMenuState(menu,id,MF_BYCOMMAND);
}

extern "C" __declspec(dllexport) int WinApi_AddToTray(const void *in,int in_size)
{
	NOTIFYICONDATA tray;
	ZeroMemory(&tray,sizeof(tray));
	HWND window = *(HWND *)in;
	in = (int *)in+1;
	int len_tip = *(int *)in; 
	in = (int *)in+1;	
	for (int i=0; i<len_tip+1; i++)
	{
		tray.szTip[i] = *(char *)in;
		in = (char *)in+sizeof(char);
	}	
	tray.hIcon = LoadIcon(NULL,IDI_APPLICATION);
	tray.hWnd = window;	
	tray.uCallbackMessage = WM_MOUSEMOVE;
	tray.uFlags = NIF_TIP | NIF_ICON | NIF_MESSAGE;
	CloseWindow(window);
	ShowWindow(window,SW_HIDE);
	return Shell_NotifyIcon(NIM_ADD,&tray);
}