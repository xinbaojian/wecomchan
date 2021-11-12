package models

// Msg 基础消息对象
type Msg struct {
	ToUser  string `json:"touser"`
	AgentId string `json:"agentid"`
	MsgType string `json:"msgtype"`
}

// News 图文对象
type News struct {
	Articles *[]WecomArticle `json:"articles"`
}

// WecomArticle 图文消息
type WecomArticle struct {
	Title       string `json:"title"`
	Description string `json:"description"`
	Url         string `json:"url"`
	PicUrl      string `json:"picurl"`
}

type Text struct {
	Content string `json:"content"`
}

// TextCard 文本卡片消息
type TextCard struct {
	Title       string `json:"title"`
	Description string `json:"description"`
	Url         string `json:"url"`
	BtnTxt      string `json:"btntxt"`
}

// NewsMsg 图文消息对象
type NewsMsg struct {
	Msg
	News News `json:"news"`
}

// TextMsg 文本消息对象
type TextMsg struct {
	Msg
	Text Text `json:"text"`
}

type TextCardMsg struct {
	Msg
	TextCard *TextCard `json:"textcard"`
}
