//武将分解与组合(合成)
package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.NpcInfoDao;
import com.shinian.dao.NpcPieceDao;
import com.shinian.dao.PlayerInfoDao;
import com.shinian.dao.PropInfoDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.DateUtil;
import com.shinian.util.Message;
import com.shinian.util.RandomUtil;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.CommonVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcFenjieVo;
import com.shinian.vo.NpcHechengVo;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.vo.NpcPieceVo;
import com.shinian.vo.PlayerInfoVo;
import com.shinian.vo.PropInfoVo;



@Service
public class NpcFenjieHechengService {
	
	@Autowired
	NpcInfoDao npcInfoDao;
	
	@Autowired
	PlayerInfoDao playerInfoDao;
	
	@Autowired
	NpcPieceDao npcPieceDao;
	
	@Autowired
	PropInfoDao propInfoDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	NpcAddService npcAddService;
	
	//进阶丹，五彩石，虎符，地煞令，天罡令，圣将令，隋唐军功章 
    private static final int[] COM_ID = {7,8,9,10,11,12,13}; 
    //三星万能将魂，四星万能将魂，五星万能将魂
    private static final int[] NPC_PIECE_ID = {501,502,503}; 	

	//武将分解得到银两、合成材料和万能将魂
	public MessageRespVo npcFenjie(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		NpcFenjieVo npcFenjieVo = JSON.parseObject(gcrv.getData().toString(),NpcFenjieVo.class);
		result.setTs(gcrv.getTs());
		
		if ( null == npcFenjieVo.getNpcList() || null == npcFenjieVo.getUid()) {
			result.setCode(Message.MSG_CODE_PLAYERID_IS_NULL);
			result.setMsg(Message.MSG_PLAYERID_IS_NULL);
			return result;
		}
		
		PlayerInfoVo piv = playerInfoDao.getPlayerInfoByUid(npcFenjieVo.getUid());
		NpcFenjieVo nfrv = new NpcFenjieVo();
		nfrv.init();
		
		for( CommonVo commonVo : npcFenjieVo.getNpcList()){
			NpcInfoVo npcInfoVo = npcInfoDao.getNpcInfoById(commonVo.getId());
			if( npcInfoVo ==null ){
				result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
				result.setMsg(Message.MSG_NPC_NOT_EXIST);
				return result;
			}
			//不是此玩家的武将
			if( !npcFenjieVo.getUid().equals(npcInfoVo.getUid())){
				result.setCode(Message.MSG_CODE_SEL_NPC_ERROR);
				result.setMsg(Message.MSG_SEL_NPC_ERROR);
				return result;
			}
			
			for( int i=0; i<=npcInfoVo.getPinjie(); i++){
				NpcInfoRedisVo nirv = redisCacheUtil.getNpcInfoByComId(npcInfoVo.getComId());
				int[][] num;			
			
				switch( nirv.getStar() ){
				case 3:
					//三星将魂
					num = new int[3][2];
					num[0][0] = 3;
					num[1][0] = 4;
					num[2][0] = 5;
					num[0][1] = 1;
					num[1][1] = 1;
					num[2][1] = 1;
					nfrv.setH3(getComRand(num) + nfrv.getH3());
					//虎符
					num = new int[3][2];
					num[0][0] = 0;
					num[1][0] = 1;
					num[2][0] = 2;
					num[0][1] = 3;
					num[1][1] = 5;
					num[2][1] = 2;
					nfrv.setT(getComRand(num) + nfrv.getT());

					piv.setSilver(piv.getSilver()+500);
					
					break;
				case 4:
					//四星将魂
					num = new int[7][2];
					num[0][0] = 4;
					num[1][0] = 5;
					num[2][0] = 6;
					num[3][0] = 7;
					num[4][0] = 8;
					num[5][0] = 9;
					num[6][0] = 10;
					num[0][1] = 1;
					num[1][1] = 1;
					num[2][1] = 1;
					num[3][1] = 1;
					num[4][1] = 1;
					num[5][1] = 1;
					num[6][1] = 1;
					nfrv.setH4(getComRand(num) + nfrv.getH4());

					//地煞令
					num = new int[3][2];
					num[0][0] = 0;
					num[1][0] = 1;
					num[0][1] = 1;
					num[1][1] = 2;
					nfrv.setE(getComRand(num) + nfrv.getE());
					//进阶丹
					num = new int[2][2];
					num[0][0] = 0;
					num[1][0] = 1;
					num[0][1] = 1;
					num[1][1] = 1;
					nfrv.setJ(getComRand(num) + nfrv.getJ());					
					piv.setSilver(piv.getSilver()+5000);
					
					break;
				case 5:
					//五星将魂
					num = new int[7][2];
					num[0][0] = 2;
					num[1][0] = 3;
					num[2][0] = 4;
					num[3][0] = 5;
					num[4][0] = 6;
					num[5][0] = 7;
					num[6][0] = 8;
					num[0][1] = 1;
					num[1][1] = 1;
					num[2][1] = 1;
					num[3][1] = 1;
					num[4][1] = 1;
					num[5][1] = 1;
					num[6][1] = 1;
					nfrv.setH5(getComRand(num) + nfrv.getH5());

					//天罡令
					num = new int[2][2];
					num[0][0] = 0;
					num[1][0] = 1;
					num[0][1] = 1;
					num[1][1] = 1;
					nfrv.setP(getComRand(num) + nfrv.getP());
					//进阶丹
					num = new int[5][2];
					num[0][0] = 0;
					num[1][0] = 1;
					num[2][0] = 2;
					num[3][0] = 3;
					num[4][0] = 4;
					num[0][1] = 4;
					num[1][1] = 6;
					num[2][1] = 4;
					num[3][1] = 1;
					num[4][1] = 1;
					nfrv.setJ(getComRand(num) + nfrv.getJ());
					
					piv.setSilver(piv.getSilver()+50000);

					break;
				default:
					result.setCode(Message.MSG_CODE_NPC_FENJIE_EXCEPTION);
					result.setMsg(Message.MSG_NPC_FENJIE_EXCEPTION);
					return result;
				}
			}
			npcInfoDao.deleteNpcById(commonVo.getId());
		}
		
		nfrv.setNpcList(npcFenjieVo.getNpcList());
		
		//写数据库
		playerInfoDao.updatePlayer(piv);		//增加钱
		nfrv.setSliver(piv.getSilver());

		//写武将将魂数据库
		NpcPieceVo npv = new NpcPieceVo();		
		npv.setUid(npcFenjieVo.getUid());		

		if( nfrv.getH3()>0 ){
			npv.setAmount(nfrv.getH3());
			npv.setComId(NPC_PIECE_ID[0]);
			updateNpcPieceDB(npv);
		}
		if( nfrv.getH4()>0 ){
			npv.setAmount(nfrv.getH4());
			npv.setComId(NPC_PIECE_ID[1]);
			updateNpcPieceDB(npv);
		}
		if( nfrv.getH5()>0 ){
			npv.setAmount(nfrv.getH5());
			npv.setComId(NPC_PIECE_ID[2]);
			updateNpcPieceDB(npv);
		}
		
		//写玩家物品数据库
		PropInfoVo propInfoVo = new PropInfoVo();
		propInfoVo.setUid(npcFenjieVo.getUid());
		
		if( nfrv.getJ()>0 ){
			propInfoVo.setComId(COM_ID[0]);
			propInfoVo.setAmount(nfrv.getJ());
			updateNpcPropDB(propInfoVo);
		}
		if( nfrv.getF()>0 ){
			propInfoVo.setComId(COM_ID[1]);
			propInfoVo.setAmount(nfrv.getF());
			updateNpcPropDB(propInfoVo);
		}
		if( nfrv.getT()>0 ){
			propInfoVo.setComId(COM_ID[2]);
			propInfoVo.setAmount(nfrv.getT());
			updateNpcPropDB(propInfoVo);
		}
		if( nfrv.getE()>0 ){
			propInfoVo.setComId(COM_ID[3]);
			propInfoVo.setAmount(nfrv.getE());
			updateNpcPropDB(propInfoVo);
		}
		if( nfrv.getP()>0 ){
			propInfoVo.setComId(COM_ID[4]);
			propInfoVo.setAmount(nfrv.getP());
			updateNpcPropDB(propInfoVo);
		}		

		result.setData(nfrv);	
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	private void updateNpcPropDB(PropInfoVo propInfoVo){
		
		PropInfoVo piv = propInfoDao.getPropOfPlayerByComId(propInfoVo.getUid(), propInfoVo.getComId());
		if( piv==null ){
			propInfoDao.addPropertyToPlayer(propInfoVo.getUid(), propInfoVo.getComId(), propInfoVo.getAmount());
		}
		else{
			piv.setAmount(piv.getAmount()+propInfoVo.getAmount());
			propInfoDao.updatePropertyOfPlayer(piv);
		}
		
	}
	
	private void updateNpcPieceDB(NpcPieceVo npcPieceVo){
		
		NpcPieceVo npv = npcPieceDao.getNpcPieceBy2Id(npcPieceVo.getComId(), npcPieceVo.getUid());
		if( npv==null ){
			npv = new NpcPieceVo();
			npv.setComId(npcPieceVo.getComId());
			npv.setUid(npcPieceVo.getUid());
			npv.setAmount(npcPieceVo.getAmount());
			npv.setUpdateTime(DateUtil.getCurrentTime());
			npcPieceDao.insertNpcPiece(npv);
		}
		else{
			npv.setUpdateTime(DateUtil.getCurrentTime());
			npv.setAmount(npv.getAmount()+npcPieceVo.getAmount());
			npcPieceDao.updateNpcPiece(npv);
		}
		
	}
	
	//功能：获得掉落物品数量
	//参数：nAw数字和权值
	private int getComRand(int[][] nAw){
		
		int arrayLength = nAw.length;
		if( arrayLength==0 ){
			return 0;
		}
		if( nAw[0].length != 2 ){
			return 0;
		}
		
		int max = 0;
		for( int i=0; i<arrayLength; i++ ){			
			max += nAw[i][1];
		}
		
		int[] tempRand = RandomUtil.random(1, max, 1);
		
		max = 0;
		int rtnRand = 0;
		for( int i=0; i<arrayLength; i++ ){			
			max += nAw[i][1];
			if( tempRand[0] < max ){
				rtnRand = nAw[i][0];
				break;
			}
		}		
		
		return rtnRand;
	}
	
	//武将组合(合成)
	public MessageRespVo npcHecheng(HttpServletRequest request, HttpServletResponse response,String jsonStr){
		
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		NpcHechengVo npcHechengVo = JSON.parseObject(gcrv.getData().toString(),NpcHechengVo.class);
		result.setTs(gcrv.getTs());
		
		if ( null == npcHechengVo.getUid() || 0 == npcHechengVo.getCId()) {
			result.setCode(Message.MSG_CODE_PLAYERID_IS_NULL);
			result.setMsg(Message.MSG_PLAYERID_IS_NULL);
			return result;
		}
		
		//某人pieceId和comId相同，建议删除common表中的pieceId
		NpcInfoRedisVo nirv = redisCacheUtil.getNpcInfoByComId(npcHechengVo.getCId());
		if( nirv==null ){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}
		
		//要合成的武将与万能将魂星级不同
		if( nirv.getStar()>=3 && nirv.getStar()<=5 ){
			if( npcHechengVo.getGId() != NPC_PIECE_ID[nirv.getStar()-3] ){				
				result.setCode(Message.MSG_CODE_NPC_HECHENG_EXCEPTION);
				result.setMsg(Message.MSG_NPC_HECHENG_EXCEPTION);
				return result;
			}
		}
		else{
			result.setCode(Message.MSG_CODE_NPC_HECHENG_EXCEPTION);
			result.setMsg(Message.MSG_NPC_HECHENG_EXCEPTION);
			return result;
		}
		
		if( nirv.getPieces() != npcHechengVo.getCNum() + npcHechengVo.getGNum() ){	
			result.setCode(Message.MSG_CODE_NPC_HECHENG_NUM_ERROR);
			result.setMsg(Message.MSG_NPC_HECHENG_NUM_ERROR);
			return result;
		}
		if( npcHechengVo.getGNum()> nirv.getMaxPieces()){
			result.setCode(Message.MSG_CODE_NPC_COMMON_PIECE_ERROR);
			result.setMsg(Message.MSG_NPC_COMMON_PIECE_ERROR);
			return result;
		}
		
		NpcPieceVo npv = npcPieceDao.getNpcPieceBy2Id(npcHechengVo.getCId(), npcHechengVo.getUid());
		if( npv==null ){
			result.setCode(Message.MSG_CODE_NPC_PIECE_NOT_ENOUGH);
			result.setMsg(Message.MSG_NPC_PIECE_NOT_ENOUGH);
			return result;
		}
		if( npv.getAmount()< npcHechengVo.getCNum()){
			result.setCode(Message.MSG_CODE_NPC_PIECE_NOT_ENOUGH);
			result.setMsg(Message.MSG_NPC_PIECE_NOT_ENOUGH);
			return result;
		}
		npv.setAmount(npv.getAmount()-npcHechengVo.getCNum());

		
		NpcPieceVo npvWanneng = npcPieceDao.getNpcPieceBy2Id(npcHechengVo.getGId(), npcHechengVo.getUid());
		if( npvWanneng==null ){
			result.setCode(Message.MSG_CODE_COMMON_PIECE_NOT_ENOUGH);
			result.setMsg(Message.MSG_COMMON_PIECE_NOT_ENOUGH);
			return result;
		}
		if( npvWanneng.getAmount()< npcHechengVo.getGNum()){
			result.setCode(Message.MSG_CODE_COMMON_PIECE_NOT_ENOUGH);
			result.setMsg(Message.MSG_COMMON_PIECE_NOT_ENOUGH);
			return result;
		}
		npvWanneng.setAmount(npvWanneng.getAmount()-npcHechengVo.getGNum());
		
		npcPieceDao.updateNpcPiece(npv);
		npcPieceDao.updateNpcPiece(npvWanneng);
		npcAddService.addNpcToPlayer(npcHechengVo.getUid(), npcHechengVo.getCId(), 1);		
		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
}
