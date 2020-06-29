package twt.sso.curl;

public interface CurlInfo {
	int CURLINFO_STRING = 0x100000;
	int CURLINFO_LONG = 0x200000;
	int CURLINFO_DOUBLE = 0x300000;
	int CURLINFO_SLIST = 0x400000;
	int CURLINFO_MASK = 0x0fffff;
	int CURLINFO_TYPEMASK = 0xf00000;

	int CURLINFO_NONE = 0;
	int CURLINFO_EFFECTIVE_URL = CURLINFO_STRING + 1;
	int CURLINFO_RESPONSE_CODE = CURLINFO_LONG + 2;
	int CURLINFO_HTTP_CODE = CURLINFO_LONG + 2;
	int CURLINFO_TOTAL_TIME = CURLINFO_DOUBLE + 3;
	int CURLINFO_NAMELOOKUP_TIME = CURLINFO_DOUBLE + 4;
	int CURLINFO_CONNECT_TIME = CURLINFO_DOUBLE + 5;
	int CURLINFO_PRETRANSFER_TIME = CURLINFO_DOUBLE + 6;
	int CURLINFO_SIZE_UPLOAD = CURLINFO_DOUBLE + 7;
	int CURLINFO_SIZE_DOWNLOAD = CURLINFO_DOUBLE + 8;
	int CURLINFO_SPEED_DOWNLOAD = CURLINFO_DOUBLE + 9;
	int CURLINFO_SPEED_UPLOAD = CURLINFO_DOUBLE + 10;
	int CURLINFO_HEADER_SIZE = CURLINFO_LONG + 11;
	int CURLINFO_REQUEST_SIZE = CURLINFO_LONG + 12;
	int CURLINFO_SSL_VERIFYRESULT = CURLINFO_LONG + 13;
	int CURLINFO_FILETIME = CURLINFO_LONG + 14;
	int CURLINFO_CONTENT_LENGTH_DOWNLOAD = CURLINFO_DOUBLE + 15;
	int CURLINFO_CONTENT_LENGTH_UPLOAD = CURLINFO_DOUBLE + 16;
	int CURLINFO_STARTTRANSFER_TIME = CURLINFO_DOUBLE + 17;
	int CURLINFO_CONTENT_TYPE = CURLINFO_STRING + 18;
	int CURLINFO_REDIRECT_TIME = CURLINFO_DOUBLE + 19;
	int CURLINFO_REDIRECT_COUNT = CURLINFO_LONG + 20;
	int CURLINFO_PRIVATE = CURLINFO_STRING + 21;
	int CURLINFO_HTTP_CONNECTCODE = CURLINFO_LONG + 22;
	int CURLINFO_HTTPAUTH_AVAIL = CURLINFO_LONG + 23;
	int CURLINFO_PROXYAUTH_AVAIL = CURLINFO_LONG + 24;
	int CURLINFO_OS_ERRNO = CURLINFO_LONG + 25;
	int CURLINFO_NUM_CONNECTS = CURLINFO_LONG + 26;
	int CURLINFO_SSL_ENGINES = CURLINFO_SLIST + 27;
	int CURLINFO_COOKIELIST = CURLINFO_SLIST + 28;
	int CURLINFO_LASTSOCKET = CURLINFO_LONG + 29;
	int CURLINFO_FTP_ENTRY_PATH = CURLINFO_STRING + 30;
	int CURLINFO_REDIRECT_URL = CURLINFO_STRING + 31;
	int CURLINFO_PRIMARY_IP = CURLINFO_STRING + 32;
	int CURLINFO_APPCONNECT_TIME = CURLINFO_DOUBLE + 33;
	int CURLINFO_CERTINFO = CURLINFO_SLIST + 34;
	int CURLINFO_CONDITION_UNMET = CURLINFO_LONG + 35;
	int CURLINFO_RTSP_SESSION_ID = CURLINFO_STRING + 36;
	int CURLINFO_RTSP_CLIENT_CSEQ = CURLINFO_LONG + 37;
	int CURLINFO_RTSP_SERVER_CSEQ = CURLINFO_LONG + 38;
	int CURLINFO_RTSP_CSEQ_RECV = CURLINFO_LONG + 39;
	int CURLINFO_PRIMARY_PORT = CURLINFO_LONG + 40;
	int CURLINFO_LOCAL_IP = CURLINFO_STRING + 41;
	int CURLINFO_LOCAL_PORT = CURLINFO_LONG + 42;
	int CURLINFO_LASTONE = 42;
}
