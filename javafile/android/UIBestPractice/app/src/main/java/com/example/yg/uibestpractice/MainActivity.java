package com.example.yg.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsgs();
        inputText = (EditText)findViewById(R.id.input_text);
        send = (Button)findViewById(R.id.send);
        msgRecyclerView = (RecyclerView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
            }
        });
    }
    private void initMsgs(){
        Msg msg1 = new Msg("Hello guy.",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you.", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }

    public class Msg{
        public static final int TYPE_RECEIVED = 0;
        public static final int TYPE_SENT = 1;
        private String content;
        private int type;

        public Msg(String content, int type){
            this.content = content;
            this.type = type;
        }

        public String getContent(){
            return content;
        }

        public int getType(){
            return type;
        }
    }

    public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{
        private List<Msg> mMsglist;
        public class ViewHolder extends RecyclerView.ViewHolder{
            LinearLayout leftLayout;
            LinearLayout rightLayout;
            TextView leftMsg;
            TextView rightMsg;
            public  ViewHolder(View view){
                super(view);
                leftLayout = (LinearLayout)view.findViewById(R.id.left_layout);
                rightLayout = (LinearLayout)view.findViewById(R.id.right_layout);
                leftMsg = (TextView)view.findViewById(R.id.left_msg);
                rightMsg = (TextView)view.findViewById(R.id.right_msg);
            }
        }
        public MsgAdapter(List<Msg> msgList){
            mMsglist = msgList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            Msg msg = mMsglist.get(position);
            if(msg.getType() == Msg.TYPE_RECEIVED){
                holder.leftLayout.setVisibility(View.VISIBLE);
                holder.rightLayout.setVisibility(View.GONE);
                holder.leftMsg.setText(msg.getContent());
            }
            else if(msg.getType() == Msg.TYPE_SENT) {
                holder.rightLayout.setVisibility(View.VISIBLE);
                holder.leftLayout.setVisibility(View.GONE);
                holder.rightMsg.setText(msg.getContent());
            }
        }
        @Override
        public int getItemCount(){
            return mMsglist.size();
        }
    }
}
